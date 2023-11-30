package fr.leroideskiwis.l3bot.questions;

import fr.leroideskiwis.l3bot.listeners.QuestionExecutor;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;

import java.util.Map;

public class QuestionExecutorImpl implements QuestionExecutor {
    @Override
    public void execute(MessageChannel channel, Map<String, String> questions) {
        questions.forEach((key, value) -> channel.sendMessage(key + " : " + value).queue());
    }
}
