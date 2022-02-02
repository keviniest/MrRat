package com.keviniest.mrRat.commands.impl;

import com.keviniest.mrRat.commands.Command;
import com.keviniest.mrRat.commands.CommandManager;

public class EatCheeseCommand extends Command {


    public EatCheeseCommand() {
        super("eatcheese");
    }

    @Override
    public void onCommand(String[] args, String command) {

        if(args.length == 0) {
            CommandManager.cheeseAte++;
            CommandManager.send("Total cheese ate : " + CommandManager.cheeseAte + "!");
        } else if(args.length == 1) {
            if(CommandManager.event.getAuthor().toString().equalsIgnoreCase("U:Keviniest(682501040561848335)")) {
                CommandManager.cheeseAte = Long.parseLong(CommandManager.cheeseAte + args[0]);
                CommandManager.send("Total cheese ate : " + CommandManager.cheeseAte + "!");
            } else {
                CommandManager.send("Only Managers can do that");
                CommandManager.cheeseAte++;
                CommandManager.send("Total cheese ate : " + CommandManager.cheeseAte + "!");
            }
        } else {
            CommandManager.send("To many arguments given.");
        }
    }
}
