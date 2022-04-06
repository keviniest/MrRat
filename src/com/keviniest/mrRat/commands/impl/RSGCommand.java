package com.keviniest.mrRat.commands.impl;

import com.keviniest.mrRat.commands.Command;
import com.keviniest.mrRat.utils.CrowdSourcingUtil;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.io.FileNotFoundException;

public class RSGCommand extends Command {

	public RSGCommand() {
		super("rsg");
	}

	@Override
	public void onCommand(String[] args, String command, GuildMessageReceivedEvent event) {
		if (args[0].equalsIgnoreCase("add")) {
			if ((CrowdSourcingUtil.add(args[1], args[2], event.getAuthor()))) {
				event.getChannel().sendMessage("Word added!").queue();
			} else {
				event.getChannel().sendMessage("Invalid type for `" + args[1] + "` (subj, verb, obj)").queue();
			}
		} else if (args[0].equalsIgnoreCase("print")) {
			try {
				event.getChannel().sendMessage(CrowdSourcingUtil.randomSentence()).queue();
			} catch (FileNotFoundException e) {
				event.getChannel().sendMessage(e.toString()).queue();
			}
		} else {
			event.getChannel().sendMessage("" +
					"❌Invalid option fed, check https://docs.google.com/document/d/1EfEFHPe7GSXM0OQ7xJ2NwToQv69DmAdN0vl8GHIw1_I/edit?usp=sharing%22" +
					"").queue();
		}
	}
}
