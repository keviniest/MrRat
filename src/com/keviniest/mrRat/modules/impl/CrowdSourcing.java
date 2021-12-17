package com.keviniest.mrRat.modules.impl;

import com.keviniest.mrRat.MrRat;
import com.keviniest.mrRat.util.CrowdSourcingUtil;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.io.FileNotFoundException;

public class CrowdSourcing extends ListenerAdapter {
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split("\\s+");

        if(args[0].equalsIgnoreCase(MrRat.prefix + "rsg")) {
            if(args[1].equalsIgnoreCase("add")) {
                CrowdSourcingUtil.add(args[2], args[3], event.getAuthor());
                event.getChannel().sendMessage("Word Added!").queue();
            }
            else if(args[1].equalsIgnoreCase("print")) {
                try {
                    event.getChannel().sendMessage(CrowdSourcingUtil.randomSentence()).queue();
                } catch (FileNotFoundException e) {
                    event.getChannel().sendMessage(e.toString()).queue();
                }
            }
            else {
                event.getChannel().sendMessage("❌Invalid option fed, check https://docs.google.com/document/d/1EfEFHPe7GSXM0OQ7xJ2NwToQv69DmAdN0vl8GHIw1_I/edit?usp=sharing").queue();
            }
        }
    }
}
