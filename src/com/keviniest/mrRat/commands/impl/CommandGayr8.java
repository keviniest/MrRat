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
        CommandManager.event.getChannel().sendMessage(":rainbow_flag:  You are " + howGay + "% gay.:rainbow:").queue();
    }
}
