package com.keviniest.mrRat.commands.impl;

import com.keviniest.mrRat.commands.Command;
import com.keviniest.mrRat.commands.CommandManager;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class PrefixCommand extends Command {

	public PrefixCommand() {
		super("prefix");
	}

	@Override
	public void onCommand(String[] args, String command, GuildMessageReceivedEvent event) {
		event.getChannel().sendMessage("`" + CommandManager.prefix + "` is the current prefix").queue();
	}
}
