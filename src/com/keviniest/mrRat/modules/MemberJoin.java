package com.keviniest.mrRat.modules;

import java.util.Random;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class MemberJoin extends ListenerAdapter {
	
	String[] joinMessage = {
		"[member] escorted by Ramin to the server.",
		"[member] has join the party, hope they brought cheese.",
		"Welcome [member] to the War of Cheese!",
		"[member] was dipped into the cheese fondue while joining the server"
	};
	
	@SuppressWarnings("deprecation")
	public void onGuildMemberJoin(GuildMemberJoinEvent event) {
		System.out.println("member has joined"); //TODO
		Random rand = new Random();
		int number = rand.nextInt(joinMessage.length);
		
		EmbedBuilder join = new EmbedBuilder();
		join.setColor(Commands.embedColor);
		join.setDescription(joinMessage[number].replace("[member]", event.getMember().getAsMention()));
		
		event.getGuild().getDefaultChannel().sendMessage(join.build()).queue();
		
		//String message = joinMessage[number].replace("[member]", event.getMember().getAsMention());
		//event.getGuild().getDefaultChannel().sendMessage(message);
	}
}
