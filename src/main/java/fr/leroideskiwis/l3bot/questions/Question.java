package fr.leroideskiwis.l3bot.questions;

import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;

public abstract class Question {

    protected String question;
    private final QuestionType type;

    public Question(String question, QuestionType questionType) {
        this.question = question;
        this.type = questionType;
    }

    public abstract String parseAnswer(String answer);

    public abstract void sendQuestion(MessageChannel textChannel);

    public boolean isType(QuestionType type){
        return this.type == type;
    }

    public boolean isValid(String answer){
        return true;
    }

    @Override
    public String toString() {
        return question;
    }

    public enum QuestionType{
        REACTION, TEXT
    }
}
