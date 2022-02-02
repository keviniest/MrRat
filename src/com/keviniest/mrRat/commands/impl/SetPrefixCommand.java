package com.keviniest.mrRat.commands.impl;

import com.keviniest.mrRat.MrRat;
import com.keviniest.mrRat.commands.Command;
import com.keviniest.mrRat.commands.CommandManager;

public class SetPrefixCommand extends Command {

    public SetPrefixCommand() {
        super("setprefix");
    }

    @Override
    public void onCommand(String[] args, String command) {
        if(CommandManager.event.getAuthor().toString().equalsIgnoreCase("U:Keviniest(682501040561848335)")) {
            CommandManager.prefix.delete(0, CommandManager.prefix.length());
            CommandManager.prefix.append(args[0]);
            CommandManager.send("`" + CommandManager.prefix + "` is the new prefix");
        } else {
            CommandManager.send("❌You do not have permission to do that");
        }
    }
}
