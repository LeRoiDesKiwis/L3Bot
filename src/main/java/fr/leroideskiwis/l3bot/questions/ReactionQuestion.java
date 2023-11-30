package fr.leroideskiwis.l3bot.questions;

import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.entities.emoji.Emoji;

import java.util.HashMap;
import java.util.Map;

public class ReactionQuestion extends Question{
    private final Map<String, String> emotes;

    public ReactionQuestion(String question, Map<String, String> emotes){
        super(question, QuestionType.REACTION);
        this.emotes = emotes;
    }

    @Override
    public String parseAnswer(String answer) {
        return emotes.get(answer);
    }

    @Override
    public void sendQuestion(MessageChannel textChannel) {
        textChannel.sendMessage(question).queue(message -> {
            for(String emote : emotes.keySet()){
                message.addReaction(Emoji.fromUnicode(emote)).queue();
            }
        });
    }

    public static class ReactionQuestionBuilder{
        private final Map<String, String> emotes = new HashMap<>();
        private final String question;

        public ReactionQuestionBuilder(String question){
            this.question = question;
        }

        public ReactionQuestionBuilder addReaction(String emoji, String answer){
            this.emotes.put(emoji, answer);
            return this;
        }

        public ReactionQuestion build(){
            return new ReactionQuestion(question, emotes);
        }
    }
}
