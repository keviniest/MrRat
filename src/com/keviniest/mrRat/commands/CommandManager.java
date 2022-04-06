package com.keviniest.mrRat.commands;

import com.keviniest.mrRat.MrRat;
import com.keviniest.mrRat.commands.impl.*;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Improved command handling system
 * https://www.youtube.com/watch?v=o8fuBYUB6cI
 */
public class CommandManager extends ListenerAdapter {

	public List<Command> commands = new ArrayList<>();
	public static final int EMBED_COLOR = 0x644E3F;
	public static long cheeseAte = 0L;
	public static StringBuffer prefix = new StringBuffer("~");

	public CommandManager() {
		commands.add(new RSGCommand());
		commands.add(new HelpCommand());
		commands.add(new Gayr8Command());
		commands.add(new InfoCommand());
		commands.add(new EatCheeseCommand());
		commands.add(new PrefixCommand());
		commands.add(new SetPrefixCommand());
		commands.add(new RateCommand());
	}

	public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
		String message = event.getMessage().getContentRaw();

		if (MrRat.debug) {
			if (!event.getAuthor().toString().equals("U:Keviniest(682501040561848335)")) {
				return;
			}
		}

		if (!message.startsWith(String.valueOf(CommandManager.prefix)) || event.getAuthor().isBot()) {
			return;
		}

		message = message.substring(CommandManager.prefix.length());

		boolean commandFound = false;

		if (message.split("\\s+")[0].length() > 0) {
			String commandName = message.split("\\s+")[0];

			for (Command c : commands) {
				if (c.name.equalsIgnoreCase(commandName)) {
					c.onCommand(Arrays.copyOfRange(message.split("\\s+"), 1, message.split("\\s+").length), message, event);
					commandFound = true;
					break;
				}
			}

		}
		if (!commandFound) {
			event.getChannel().sendMessage("Command not found; `~help` for help").queue();
		}
	}
}
