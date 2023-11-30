package fr.leroideskiwis.l3bot.questions;

import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;

import java.util.List;

public class QuestionChoice extends Question{

    private final String[] choices;

    public QuestionChoice(String question, Object... choices) {
        super(question, QuestionType.TEXT);
        this.choices = new String[choices.length];
        for(int i = 0; i < choices.length; i++){
            this.choices[i] = choices[i].toString();
        }
    }

    @Override
    public String parseAnswer(String answer) {
        return answer;
    }

    @Override
    public void sendQuestion(MessageChannel textChannel) {
        textChannel.sendMessage(question+" (Choices: "+String.join(", ", choices)+")").queue();
    }

    @Override
    public boolean isValid(String answer) {
        return List.of(choices).contains(answer);
    }
}
