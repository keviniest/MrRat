package com.keviniest.mrRat.commands.impl;

import com.keviniest.mrRat.commands.Command;
import com.keviniest.mrRat.commands.CommandManager;
import com.keviniest.mrRat.utils.CrowdSourcingUtil;

import java.io.FileNotFoundException;

public class RSGCommand extends Command {

    public RSGCommand() {
        super("rsg");
    }

    @Override
    public void onCommand(String[] args, String command) {
        if(args[0].equalsIgnoreCase("add")) {
            CrowdSourcingUtil.add(args[1], args[2], CommandManager.event.getAuthor());
            CommandManager.event.getChannel().sendMessage("Word Added!").queue();
        }
        else if(args[0].equalsIgnoreCase("print")) {
            try {
                CommandManager.event.getChannel().sendMessage(CrowdSourcingUtil.randomSentence()).queue();
            } catch (FileNotFoundException e) {
                CommandManager.event.getChannel().sendMessage(e.toString()).queue();
            }
        }
        else {
            CommandManager.event.getChannel().sendMessage("" +
                    "❌Invalid option fed, check https://docs.google.com/document/d/1EfEFHPe7GSXM0OQ7xJ2NwToQv69DmAdN0vl8GHIw1_I/edit?usp=sharing%22" +
                    "").queue();
        }
    }
}
