package com.keviniest.mrRat.commands.impl;

import com.keviniest.mrRat.commands.Command;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.util.Random;

public class Gayr8Command extends Command {

	public Gayr8Command() {
		super("gayr8");
	}

	@Override
	public void onCommand(String[] args, String command, GuildMessageReceivedEvent event) {
		Random rand = new Random();
		int howGay = rand.nextInt(100);

		if (args.length == 0) {
			event.getChannel().sendMessage("You are " + howGay + "% gay.:rainbow_flag: ").queue();
		} else {
			event.getChannel().sendMessage(args[0] + " is " + howGay + "% gay.:rainbow_flag: ").queue();
		}
	}
}
