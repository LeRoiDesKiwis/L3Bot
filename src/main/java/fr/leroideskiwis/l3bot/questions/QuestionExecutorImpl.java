package fr.leroideskiwis.l3bot.questions;

import fr.leroideskiwis.l3bot.Constants;
import fr.leroideskiwis.l3bot.listeners.QuestionExecutor;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;

import java.awt.*;
import java.util.Map;

public class QuestionExecutorImpl implements QuestionExecutor {
    @Override
    public void execute(Member member, MessageChannel channel, Map<String, String> questions) {
        EmbedBuilder builder = new EmbedBuilder()
                .setTitle("Presentation de " + member.getUser().getName())
                .setColor(Color.RED)
                .setThumbnail(member.getUser().getAvatarUrl());
        for(Map.Entry<String, String> entry : questions.entrySet()){
            builder.addField(entry.getKey(), entry.getValue(), false);
        }
        channel.getJDA().getGuildById(Constants.GUILDID).getChannelById(TextChannel.class, Constants.LOGCHANNELID)
                .sendMessageEmbeds(builder.build()).queue();
    }
}
