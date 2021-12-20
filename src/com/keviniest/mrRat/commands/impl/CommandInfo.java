package com.keviniest.mrRat.commands.impl;

import com.keviniest.mrRat.commands.Command;
import com.keviniest.mrRat.commands.CommandManager;
import net.dv8tion.jda.api.EmbedBuilder;

public class CommandInfo extends Command {

    public CommandInfo() {
        super("info");
    }

    @Override
    public void onCommand(String[] args, String command) {
        EmbedBuilder info = new EmbedBuilder();
        info.setTitle("🧀MrRat🐀");
        info.addField("Creator", "Keviniest#9805", true);
        info.addField("How to get started", "~help", true);
        info.addField("Source Code", "https://github.com/keviniest/MrRat", true);
        info.setDescription("Cheese bot that cheese");
        info.setThumbnail("https://cdn.discordapp.com/attachments/831478153716432930/880352202764472320/01-rat-friends-nationalgeographic_1162144.png");
        info.setColor(CommandManager.EMBED_COLOR);
        info.setFooter("Made by Keviniest", "https://cdn.discordapp.com/avatars/682501040561848335/d53739b25cb6be1a73d0b352cdbbe890.png?size=256");

        CommandManager.event.getChannel().sendMessage(info.build()).queue();
        info.clear();
    }
}
