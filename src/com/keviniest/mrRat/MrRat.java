package com.keviniest.mrRat;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javax.security.auth.login.LoginException;

import com.keviniest.mrRat.commands.CommandManager;

import com.keviniest.mrRat.events.GuildJoin;
import com.keviniest.mrRat.events.MemberJoin;
import com.keviniest.mrRat.util.CrowdSourcingUtil;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;

public class MrRat {

	public static StringBuffer prefix = new StringBuffer("~");
	public static JDA jda;
	public static boolean debug;

	public static void main(String[] args) throws LoginException, IOException {

		if(args.length == 1) {
			debug = args[0].equalsIgnoreCase("debug");
		} else {
			debug = false;
		}

		System.out.print((debug) ? "App started with debug mode\n" : "");

		jda = JDABuilder.createDefault(MrRat.readToken()).build();
		jda.getPresence().setStatus(OnlineStatus.ONLINE);
		jda.getPresence().setPresence(Activity.playing("cheese🧀"), true);

		CrowdSourcingUtil.init();
		jda.addEventListener(new CommandManager());
		jda.addEventListener(new GuildJoin());
		jda.addEventListener(new MemberJoin());
	}
	
	private static String readToken() throws FileNotFoundException {
		String token = "";
		String file = new File("").getAbsolutePath();
		file = file.concat("\\token.txt");
		Scanner scanner = new Scanner(new File(file));
		while(scanner.hasNextLine()) {
			token = scanner.nextLine();
		}
		scanner.close();
		return token;
	}
}
