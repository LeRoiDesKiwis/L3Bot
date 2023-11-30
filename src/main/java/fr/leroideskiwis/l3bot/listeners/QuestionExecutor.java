package fr.leroideskiwis.l3bot.listeners;

import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;

import java.util.Map;

public interface QuestionExecutor {

    /**
     * Is called when all questions are answered
     * @param channel the channel where the questions were asked
     * @param questions the questions and answers
     */
    void execute(MessageChannel channel, Map<String, String> questions);
}
