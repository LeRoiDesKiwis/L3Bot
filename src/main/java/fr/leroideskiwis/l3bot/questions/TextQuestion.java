package fr.leroideskiwis.l3bot.questions;

import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;

public class TextQuestion extends Question{
    public TextQuestion(String question) {
        super(question, QuestionType.TEXT);
    }

    @Override
    public String parseAnswer(String answer) {
        return answer;
    }

    @Override
    public void sendQuestion(MessageChannel textChannel) {
        textChannel.sendMessage(question).queue();
    }
}
