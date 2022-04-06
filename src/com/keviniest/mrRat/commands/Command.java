package com.keviniest.mrRat.commands;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public abstract class Command {

	/**
	 * Short name (alias) of a command
	 */
	public String name;

	public Command(String name) {
		this.name = name;
	}

	public abstract void onCommand(String[] args, String command, GuildMessageReceivedEvent event);

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
