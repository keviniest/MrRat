package com.keviniest.mrRat.events;

import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.Random;

//FIXME Something is broken with this
public class MemberJoin extends ListenerAdapter {

	public static String[] messageTemps = {
			"{member} has joined the server",
			"{member} has not !joined the server",
	};

	public void onGuildMemberJoin(GuildMemberJoinEvent event) {
		Random rand = new Random();
		event.getGuild().getDefaultChannel().sendMessage(messageTemps[rand.nextInt(messageTemps.length)].replace("{member}", event.getMember().getAsMention())).queue();
	}
}
