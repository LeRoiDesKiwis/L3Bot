package fr.leroideskiwis.l3bot;

import fr.leroideskiwis.l3bot.listeners.QuestionListener;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

public class Main {

    private JDA jda;

    public static void main(String[] args) {
        new Main(args.length > 0 ? args[0] : "");
    }

    public Main(String token){
        this.jda = JDABuilder.createDefault(token)
                .addEventListeners(new QuestionListener())
                .build();
    }
}