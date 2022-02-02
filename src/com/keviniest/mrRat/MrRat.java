package com.keviniest.mrRat;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javax.security.auth.login.LoginException;

import com.keviniest.mrRat.commands.CommandManager;

import com.keviniest.mrRat.events.GuildJoin;
import com.keviniest.mrRat.events.MemberJoin;
import com.keviniest.mrRat.utils.CrowdSourcingUtil;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;

public class MrRat {

	public static JDA jda;
	public static boolean debug = false;
	public static boolean nolog = false;

	public static void main(String[] args) throws LoginException, IOException {

		for(String s : args) {
			if(s.equals("--debug")) {
				debug = true;
			} else if(s.equals("--no-log")) {
				nolog = true;
			}
		}

		System.out.print((debug) ? "App started with debug mode\n" : "");
		System.out.print((nolog) ? "App started with nolog\n" : "");

		jda = JDABuilder.createDefault(MrRat.readToken()).build();
		jda.getPresence().setStatus(OnlineStatus.ONLINE);
		jda.getPresence().setPresence(Activity.playing("Cheese🧀"), true);

		jda.addEventListener(new CommandManager());
		jda.addEventListener(new GuildJoin());
		jda.addEventListener(new MemberJoin());

		CrowdSourcingUtil.init();

	}

	/**
	 * Reads and returns last line of file named "token.txt" from the same directory as the jar file
	 * @return last line of file named "token.txt"
	 * @throws FileNotFoundException When "token.txt" is not found (must be in same location as the jar file)
	 */
	private static String readToken() throws FileNotFoundException {
		String token = "";
		File file = new File("token.txt");
		Scanner scanner = new Scanner(file);
		while(scanner.hasNextLine()) {
			token = scanner.nextLine();
		}
		scanner.close();
		return token;
	}
}
