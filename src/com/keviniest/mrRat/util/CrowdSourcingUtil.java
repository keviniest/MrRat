package com.keviniest.mrRat.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;
import java.util.Scanner;

import net.dv8tion.jda.api.entities.User;

public class CrowdSourcingUtil {

	public static Random random = new Random();
	public static FileWriter writer;
	public static String[] prep = { "on", "at", "inside", "in", "behind" };
	public static String[] conj = { "and", "or", "but", "nor" };

	private static File nouns;
	private static File verbs;
	private static File objs;

	public static void init() {

		// Creates resources folder on same directory as the jar file
		try {
			Files.createDirectories(Paths.get("resources\\MrRat\\data"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Creates CSV files
		nouns = new File("resources\\MrRat\\data\\subjs.csv");
		try {
			nouns.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}

		verbs = new File("resources\\MrRat\\data\\verbs.csv");
		try {
			verbs.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}

		objs = new File("resources\\MrRat\\data\\objs.csv");
		try {
			objs.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Saves words to corresponding CSV file
	 *
	 * @param type type of the word (subj, verb, obj)
	 * @param word the actual word to be added
	 * @param user used for monitoring purpose. use .getAuthor() method
	 */
	public static void add(String type, String word, User user) {
		if(type.equalsIgnoreCase("subj")) {
			try {
				writeCSV(nouns, word);
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println(user + " added : " + word);
		} else if(type.equalsIgnoreCase("verb")) {
			try {
				writeCSV(verbs, word);
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println(user + " added : " + word);
		} else if(type.equalsIgnoreCase("obj")) {
			try {
				writeCSV(objs, word);
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println(user + " added : " + word);
		} else {
			System.err.println(" !!! Wrong type was given !!!");
		}
	}

	/**
	 * Base sentence is first generated using noun + verb + object format. 
	 * After that, there is a chance that the sentence will follow with a preposition or a conjunction.
	 * If it is followed by preposition, it would pick random word from objects, if it is conjunction,
	 * it would pick one random word from the subjects.
	 * 
	 * @return Randomly generated sentence using logic above
	 */
	public static String randomSentence() throws FileNotFoundException {
		boolean extended = random.nextBoolean();
		boolean isPrep = extended && random.nextBoolean();

		//First word in sentence is capitalized logic
		StringBuilder firstWord = new StringBuilder(readCSV(nouns, random.nextInt(nouns.toString().split(",").length)));
		String firstLetter = firstWord.toString().split("")[0].toUpperCase();
		firstWord = new StringBuilder(firstLetter + firstWord.delete(0, 1));

		String sentence = firstWord
				+ " "
				+ readCSV(verbs, random.nextInt(verbs.toString().split(",").length))
				+ " "
				+ readCSV(objs, random.nextInt(objs.toString().split(",").length));

		if(extended) {
			if(isPrep) {
				sentence = sentence
						+ " "
						+ prep[random.nextInt(prep.length)]
						+ " "
						+ readCSV(objs, random.nextInt(objs.toString().split(",").length));
			} else {
				sentence = sentence
						+ " "
						+ conj[random.nextInt(conj.length)]
						+ " "
						+ readCSV(nouns, random.nextInt(nouns.toString().split(",").length));
			}
		}

		sentence = sentence + ".";
		return sentence;
	}

	public static String readCSV(File type, int index) throws FileNotFoundException {
		StringBuilder word = new StringBuilder();
		Scanner scan = new Scanner(type);
		while(scan.hasNextLine()) {
			word.append(scan.nextLine());
		}
		String[] wordList = word.toString().split(",");
		scan.close();
		return wordList[index];
	}

	public static void writeCSV(File type, String word) throws IOException {
		writer = new FileWriter(type, true);
		writer.write(word + ",\n");
		writer.close();
	}

}