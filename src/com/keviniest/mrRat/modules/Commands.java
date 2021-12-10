package com.keviniest.mrRat.modules;

import java.util.Random;

import com.keviniest.mrRat.MrRat;
import com.keviniest.mrRat.util.CrowdSourcing;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Commands extends ListenerAdapter {
	
	public static final char invisibleCharacter = '⠀'; //This is here so the bot doesn't trigger to itself
	public static long cheeseAte = 0;
	public static int embedColor = 0x644E3F;
	
	@SuppressWarnings({ "deprecation"})
	public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
		String[] args = event.getMessage().getContentRaw().split("\\s+");
		Random rand = new Random();
		CrowdSourcing cs = new CrowdSourcing();

		if(args[0].equalsIgnoreCase(MrRat.prefix + "info")) {
			EmbedBuilder info = new EmbedBuilder();
			info.setTitle("🧀MrRat🐀");
			info.addField("Creator", "Keviniest#9805", true);
			info.addField("How to get help", "Ask the creator", true);
			info.addField("third field", "description here", true);
			info.addField("forth field", "description here", true);
			info.setDescription("Cheese bot that cheese");
			info.setThumbnail("https://cdn.discordapp.com/attachments/831478153716432930/880352202764472320/01-rat-friends-nationalgeographic_1162144.png");
			info.setColor(embedColor);
			info.setFooter("Made by Keviniest", "https://cdn.discordapp.com/avatars/682501040561848335/d53739b25cb6be1a73d0b352cdbbe890.png?size=256");
			
			event.getChannel().sendMessage(info.build()).queue();
			info.clear();
			
		} 
		else if(args[0].equalsIgnoreCase("cheese")) {
			event.getChannel().sendMessage("cheese" + invisibleCharacter).queue();
			
		} 
		else if(args[0].equalsIgnoreCase(MrRat.prefix + "help")) {
			event.getChannel().sendMessage("""
					`help ... Prints this message`
					`info ... Info about the bot`
					`eatcheese ... Eats a cheese`
					`prefix ... see current prefix`
					`aiaoy <parameter> ... does some fun stuff`
					`gayr8 ... Uses mings built-in line-callibrated Gaydar`
					`setprefix <new prefix> ... changes bot prefi`
					`rsg <action> <type> <word> ... use crowdsourcing to generate ramdom sentence`
					`For more info, check out` https://docs.google.com/document/d/1EfEFHPe7GSXM0OQ7xJ2NwToQv69DmAdN0vl8GHIw1_I/edit?usp=sharing\s
					""").queue();
			
		} 
		else if(args[0].equalsIgnoreCase(MrRat.prefix + "eatcheese")) {

			if(event.getAuthor().toString().equalsIgnoreCase("U:Keviniest(682501040561848335)")) {
				cheeseAte = Integer.parseInt(cheeseAte + args[1]);
			} else {
				event.getChannel().sendMessage("only the chosen ones can cheese").queue();
				cheeseAte++;
			}
			event.getChannel().sendMessage(cheeseAte + " cheese(s) ate total!").queue();
		} 
		else if(args[0].equalsIgnoreCase(MrRat.prefix + "prefix")) {
			event.getChannel().sendMessage(MrRat.prefix  + " is current prefix").queue();
			
		} 
		else if(args[0].equalsIgnoreCase(MrRat.prefix + "setprefix")) {
			if(event.getAuthor().toString().equalsIgnoreCase("U:Keviniest(682501040561848335)")) {
				MrRat.prefix.delete(0, MrRat.prefix.length());
				MrRat.prefix.append(args[1]);
				event.getChannel().sendMessage(MrRat.prefix  + " is new prefix").queue();
			} else {
				event.getChannel().sendMessage("❌You do not have permission to do that").queue();

			}
		} 
		else if(args[0].equalsIgnoreCase(MrRat.prefix + "aiaoy")) {
			event.getChannel().sendMessage("this is feature is to be implemented").queue();
		} 
		else if(args[0].equals(MrRat.prefix + "gayr8")) {
			int howGay = rand.nextInt(100);
			event.getChannel().sendMessage((args[1].isBlank()) ? "Usage : ~gayr8 <user>" : args[1] + " is " + howGay + "% gay" + ":rainbow_flag:").queue();
		} 
		else if(args[0].equalsIgnoreCase(MrRat.prefix + "rsg")) {
			if(args[1].equalsIgnoreCase("add")) {
				CrowdSourcing.add(args[2], args[3], event.getAuthor());
				event.getChannel().sendMessage("Word Added!").queue();
			} 
			else if(args[1].equalsIgnoreCase("print")) {
				event.getChannel().sendMessage(cs.randomSentence()).queue();
			}
			else if(args[1].equalsIgnoreCase("list")) {
				event.getChannel().sendMessage(CrowdSourcing.subj + "\n" + CrowdSourcing.verbs + "\n" + CrowdSourcing.objs).queue();
			} 
			else if(args[1].equalsIgnoreCase("rm")) {
				CrowdSourcing.remove(args[2], Integer.parseInt(args[3]), event.getAuthor());
				event.getChannel().sendMessage("Word Removed!").queue();
			} 
			else if(args[1].equalsIgnoreCase("set")) {
				CrowdSourcing.set(args[2], Integer.parseInt(args[3]), args[4], event.getAuthor());
				event.getChannel().sendMessage("Word Changed!").queue();
			}
			else {
				event.getChannel().sendMessage("❌Invalid option fed, check https://docs.google.com/document/d/1EfEFHPe7GSXM0OQ7xJ2NwToQv69DmAdN0vl8GHIw1_I/edit?usp=sharing").queue();
			}
		}
	}
}