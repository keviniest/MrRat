package com.keviniest.mrRat.commands.impl;

import com.keviniest.mrRat.commands.Command;
import com.keviniest.mrRat.commands.CommandManager;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class SetPrefixCommand extends Command {

	public SetPrefixCommand() {
		super("setprefix");
	}

	@Override
	public void onCommand(String[] args, String command, GuildMessageReceivedEvent event) {
		if (event.getAuthor().toString().equalsIgnoreCase("U:Keviniest(682501040561848335)")) {
			CommandManager.prefix.delete(0, CommandManager.prefix.length());
			CommandManager.prefix.append(args[0]);
			event.getChannel().sendMessage("`" + CommandManager.prefix + "` is the new prefix").queue();
		} else {
			event.getChannel().sendMessage("❌You do not have permission to do that").queue();
		}
	}
}
