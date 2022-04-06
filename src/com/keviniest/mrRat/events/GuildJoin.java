package com.keviniest.mrRat.events;

import com.keviniest.mrRat.commands.CommandManager;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class GuildJoin extends ListenerAdapter {

	public void onGuildJoin(GuildJoinEvent event) {
		EmbedBuilder embed = new EmbedBuilder();
		embed.setTitle("Hi!");
		embed.setColor(CommandManager.EMBED_COLOR);
		embed.setDescription("Thanks for add me to your server!\n" +
				"To get help, type `~help` or `~info`");
		embed.setFooter("MrRat the Discord Bot");
		event.getGuild().getDefaultChannel().sendMessage(embed.build()).queue();
	}
}
