package com.keviniest.mrRat.commands.impl;

import com.keviniest.mrRat.commands.Command;
import com.keviniest.mrRat.commands.CommandManager;

public class CommandHelp extends Command {

    public CommandHelp() {
        super("help");
    }

    @Override
    public void onCommand(String[] args, String command) {
        CommandManager.event.getChannel().sendMessage("""
					`help ... Prints this message`
					`info ... Info about the bot`
					`eatcheese ... Eats a cheese`
					`prefix ... see current prefix`
					`aiaoy <parameter> ... webserver`
					`gayr8 ... rng with some random words`
					`setprefix <new prefix> ... changes bot prefix`
					`rsg <action> <type> <word> ... use crowd sourcing to generate random sentence`
					`For more info, check out` https://docs.google.com/document/d/1EfEFHPe7GSXM0OQ7xJ2NwToQv69DmAdN0vl8GHIw1_I/edit?usp=sharing\s
					""").queue();
    }
}
