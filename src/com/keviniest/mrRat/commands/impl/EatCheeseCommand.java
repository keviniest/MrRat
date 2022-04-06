package com.keviniest.mrRat.commands.impl;

import com.keviniest.mrRat.commands.Command;
import com.keviniest.mrRat.commands.CommandManager;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class EatCheeseCommand extends Command {

	public EatCheeseCommand() {
		super("eatcheese");
	}

	@Override
	public void onCommand(String[] args, String command, GuildMessageReceivedEvent event) {

		if (args.length == 0) {
			CommandManager.cheeseAte++;
			event.getChannel().sendMessage("Total cheese ate : " + CommandManager.cheeseAte + "!").queue();
		} else if (args.length == 1) {
			if (event.getAuthor().toString().equalsIgnoreCase("U:Keviniest(682501040561848335)")) {
				CommandManager.cheeseAte = Long.parseLong(CommandManager.cheeseAte + args[0]);
				event.getChannel().sendMessage("Total cheese ate : " + CommandManager.cheeseAte + "!").queue();
			} else {
				event.getChannel().sendMessage("Only Managers can do that").queue();
				CommandManager.cheeseAte++;
				event.getChannel().sendMessage("Total cheese ate : " + CommandManager.cheeseAte + "!").queue();
			}
		} else {
			event.getChannel().sendMessage("Too many arguments given.").queue();
		}
	}
}
