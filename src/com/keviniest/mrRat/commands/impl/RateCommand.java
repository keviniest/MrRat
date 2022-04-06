package com.keviniest.mrRat.commands.impl;

import com.keviniest.mrRat.commands.Command;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.util.Random;

public class RateCommand extends Command {

	public RateCommand() {
		super("r8");
	}

	@Override
	public void onCommand(String[] args, String command, GuildMessageReceivedEvent event) {
		Random rand = new Random();
		int percentage = rand.nextInt(100);

		if (args.length == 0 || args.length == 1) {
			event.getChannel().sendMessage("Not enough argument fed.").queue();
		} else {
			event.getChannel().sendMessage(args[0] + " is " + percentage + "% " + args[1]).queue();
		}
	}
}
