package com.keviniest.mrRat;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javax.security.auth.login.LoginException;

import com.keviniest.mrRat.commands.CommandManager;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;

public class MrRat {

	public static StringBuffer prefix = new StringBuffer("~");
	public static JDA jda;

	public static void main(String[] args) throws LoginException, IOException {
		
		jda = JDABuilder.createDefault(MrRat.readToken()).build(); //Nice try.
		jda.getPresence().setStatus(OnlineStatus.ONLINE);
		jda.getPresence().setPresence(Activity.playing("~help | ~info"), true);
		
		jda.addEventListener(new CommandManager());
	}
	
	private static String readToken() throws FileNotFoundException {
		String token = "";
		File file = new File("C:\\Users\\Kevin\\OneDrive\\Desktop\\Coding Projects\\MrRat\\token.txt");
		Scanner scanner = new Scanner(file);
		while(scanner.hasNextLine()) {
			token = scanner.nextLine();
		}
		scanner.close();
		return token;
	}
}
