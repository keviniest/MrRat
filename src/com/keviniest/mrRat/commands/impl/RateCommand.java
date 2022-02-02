package com.keviniest.mrRat.commands.impl;

import com.keviniest.mrRat.commands.Command;
import com.keviniest.mrRat.commands.CommandManager;

import java.util.Random;

public class RateCommand extends Command {

	public RateCommand() {
		super("r8");
	}

	@Override
	public void onCommand(String[] args, String command) {
		Random rand = new Random();
		int percentage = rand.nextInt(100);

		if(args.length == 0 || args.length == 1) {
			CommandManager.send("Not enough argument fed.");
		} else {
			CommandManager.send(args[0] + " is " + percentage + "% " + args[1]);
		}
	}
}
