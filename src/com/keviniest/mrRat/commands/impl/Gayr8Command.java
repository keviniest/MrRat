package com.keviniest.mrRat.commands.impl;

import com.keviniest.mrRat.commands.Command;
import com.keviniest.mrRat.commands.CommandManager;

import java.util.Random;

public class Gayr8Command extends Command {

    public Gayr8Command() {
        super("gayr8");
    }

    @Override
    public void onCommand(String[] args, String command) {
        Random rand = new Random();
        int howGay = rand.nextInt(100);

        if(args.length == 0) {
            CommandManager.event.getChannel().sendMessage("You are " + howGay + "% gay.:rainbow_flag: ").queue();
        } else {
            CommandManager.event.getChannel().sendMessage(args[0]  + " is " + howGay + "% gay.:rainbow_flag: ").queue();
        }
    }
}