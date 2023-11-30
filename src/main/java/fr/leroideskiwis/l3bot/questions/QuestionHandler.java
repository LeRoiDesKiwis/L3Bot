package fr.leroideskiwis.l3bot.questions;

import fr.leroideskiwis.l3bot.listeners.QuestionExecutor;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;

import java.util.HashMap;
import java.util.Map;

public class QuestionHandler {

    private final Question[] questions;
    public final String[] answers;
    private final QuestionExecutor questionExecutor;
    private int index = 0;
    private final MessageChannel messageChannel;
    private final Member member;

    public QuestionHandler(MessageChannel channel, QuestionExecutor questionExecutor, Member member, Question... questions){
        this.questionExecutor = questionExecutor;
        this.questions = questions;
        this.answers = new String[questions.length];
        this.messageChannel = channel;
        this.member = member;
    }

    public void sendQuestion(){
        questions[index].sendQuestion(messageChannel);
    }

    public void handleAnswer(Question.QuestionType type, String content){
        Question question = questions[index];
        if(question.isType(type) && question.isValid(content)) {
            answers[index++] = question.parseAnswer(content);
            if(!isFinish()) sendQuestion();
            else questionExecutor.execute(member, messageChannel, toMap());
        }
    }

    public Map<String, String> toMap(){
        Map<String, String> map = new HashMap<>();
        for(int i = 0; i < questions.length; i++){
            map.put(questions[i].toString(), answers[i]);
        }
        return map;
    }

    public boolean isFinish(){
        return index >= questions.length;
    }

}
