package com.keviniest.mrRat.commands.impl;

import com.keviniest.mrRat.commands.Command;
import com.keviniest.mrRat.commands.CommandManager;

public class CommandEatCheese extends Command {


    public CommandEatCheese() {
        super("eatcheese");
    }

    @Override
    public void onCommand(String[] args, String command) {

        if(args.length == 0) {
            CommandManager.cheeseAte++;
            CommandManager.event.getChannel().sendMessage("Total cheese ate : " + CommandManager.cheeseAte + "!").queue();
        } else if(args.length == 1) {
            if(CommandManager.event.getAuthor().toString().equalsIgnoreCase("U:Keviniest(682501040561848335)")) {
                CommandManager.cheeseAte = Long.parseLong(CommandManager.cheeseAte + args[0]);
                CommandManager.event.getChannel().sendMessage("Total cheese ate : " + CommandManager.cheeseAte + "!").queue();
            } else {
                CommandManager.event.getChannel().sendMessage("Only Managers can do that").queue();
                CommandManager.cheeseAte++;
                CommandManager.event.getChannel().sendMessage("Total cheese ate : " + CommandManager.cheeseAte + "!").queue();
            }
        } else {
            CommandManager.event.getChannel().sendMessage("To many arguments given.").queue();
        }
    }
}
