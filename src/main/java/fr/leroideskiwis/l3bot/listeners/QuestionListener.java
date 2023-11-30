package fr.leroideskiwis.l3bot.listeners;

import fr.leroideskiwis.l3bot.Constants;
import fr.leroideskiwis.l3bot.questions.*;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class QuestionListener extends ListenerAdapter {

    private final QuestionManager questionManager;

    public QuestionListener(){
        this.questionManager = new QuestionManager(new QuestionExecutorImpl(),
                new TextQuestion("Quel est ton nom ?"),
                new QuestionChoice("En quel TD es-tu ?", 1, 2),
                new QuestionChoice("En quel TP es-tu ?", 1, 2, 3, 4, 5, 6));
    }

    @Override
    public void onGuildMemberJoin(GuildMemberJoinEvent event) {
        event.getMember().getUser().openPrivateChannel().queue(privateChannel -> {
            privateChannel.sendMessage("Bienvenue dans le serveur de L3 ! Répond à ces quelques questions pour que je puisse t'attribuer les bons rôles.").queue();
            questionManager.addUser(privateChannel, event.getMember());
        });
    }

    //for testing
    /*public void onReady(ReadyEvent event) {
        event.getJDA().retrieveUserById(327795708897787904L).complete().openPrivateChannel().queue(privateChannel -> {
            privateChannel.sendMessage("Bienvenue dans le serveur de L3 ! Repond à ces quelques questions pour que je puisse t'attribuer les bons roles.").queue();
            privateChannel.getJDA().getGuildById(Constants.GUILDID).retrieveMemberById(327795708897787904L).queue(member -> {
                questionManager.addUser(privateChannel, member);
            });
        });
    }*/

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        //if(event.getAuthor().getIdLong() != 327795708897787904L) return;
        questionManager.handle(event.getAuthor(), Question.QuestionType.TEXT, event.getMessage().getContentDisplay());
    }

    @Override
    public void onMessageReactionAdd(MessageReactionAddEvent event) {
        //if(event.getUser().getIdLong() != 327795708897787904L) return;
        questionManager.handle(event.getUser(), Question.QuestionType.REACTION, event.getEmoji().getName());
    }
}