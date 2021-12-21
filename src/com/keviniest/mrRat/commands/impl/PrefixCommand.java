package com.keviniest.mrRat.commands.impl;

import com.keviniest.mrRat.MrRat;
import com.keviniest.mrRat.commands.Command;
import com.keviniest.mrRat.commands.CommandManager;

public class PrefixCommand extends Command {

    public PrefixCommand() {
        super("prefix");
    }

    @Override
    public void onCommand(String[] args, String command) {
        CommandManager.event.getChannel().sendMessage("`" + MrRat.prefix + "` is the current prefix").queue();
    }
}
