package com.keviniest.mrRat.modules;

import com.keviniest.mrRat.MrRat;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Repeat extends ListenerAdapter {

    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split(" {2}");
        if(args[0].equalsIgnoreCase(MrRat.prefix + "repeat")) {
            event.getChannel().sendMessage(args[1]).queue();
            System.out.println(event.getAuthor() + " : " + args[1]);
        }
    }
}