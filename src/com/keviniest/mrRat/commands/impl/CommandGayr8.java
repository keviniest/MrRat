package com.keviniest.mrRat.commands.impl;

import com.keviniest.mrRat.commands.Command;
import com.keviniest.mrRat.commands.CommandManager;

import java.util.Random;

public class CommandGayr8 extends Command {

    public CommandGayr8() {
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
