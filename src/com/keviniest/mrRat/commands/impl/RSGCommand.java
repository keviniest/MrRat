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
        }
        else if(args[0].equalsIgnoreCase("print")) {
            try {
                CommandManager.send(CrowdSourcingUtil.randomSentence());
            } catch (FileNotFoundException e) {
                CommandManager.send(e.toString());
            }
        }
        else {
            CommandManager.send("" +
                    "❌Invalid option fed, check https://docs.google.com/document/d/1EfEFHPe7GSXM0OQ7xJ2NwToQv69DmAdN0vl8GHIw1_I/edit?usp=sharing%22" +
                    "");
        }
    }
}
