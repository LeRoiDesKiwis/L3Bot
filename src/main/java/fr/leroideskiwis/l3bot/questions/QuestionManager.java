package fr.leroideskiwis.l3bot.questions;

import fr.leroideskiwis.l3bot.listeners.QuestionExecutor;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;

import java.util.HashMap;
import java.util.Map;

public class QuestionManager {

    private final Map<User, QuestionHandler> questionHandlers = new HashMap<>();
    private final Question[] questions;
    private final QuestionExecutor questionExecutor;

    public QuestionManager(QuestionExecutor questionExecutor, Question... questions) {
        this.questionExecutor = questionExecutor;
        this.questions = questions;
    }

    public void addUser(MessageChannel channel, User user){
        QuestionHandler value = new QuestionHandler(channel, questionExecutor, questions);
        questionHandlers.put(user, value);
        value.sendQuestion();
    }

    public void clean(){
        for(Map.Entry<User, QuestionHandler> entrySet : new HashMap<>(questionHandlers).entrySet()){
            if(entrySet.getValue().isFinish()) questionHandlers.remove(entrySet.getKey());
        }
    }

    public void handle(User user, Question.QuestionType type, String content){
        clean();
        questionHandlers.get(user).handleAnswer(type, content);
    }
}
