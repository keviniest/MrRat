package com.keviniest.mrRat.commands.impl;

import com.keviniest.mrRat.MrRat;
import com.keviniest.mrRat.commands.Command;
import com.keviniest.mrRat.commands.CommandManager;

public class CommandSetPrefix extends Command {

    public CommandSetPrefix() {
        super("setprefix");
    }

    @Override
    public void onCommand(String[] args, String command) {
        if(CommandManager.event.getAuthor().toString().equalsIgnoreCase("U:Keviniest(682501040561848335)")) {
            MrRat.prefix.delete(0, MrRat.prefix.length());
            MrRat.prefix.append(args[0]);
            CommandManager.event.getChannel().sendMessage("`" + MrRat.prefix + "` is the new prefix").queue();
        } else {
            CommandManager.event.getChannel().sendMessage("❌You do not have permission to do that").queue();
        }
    }
}
