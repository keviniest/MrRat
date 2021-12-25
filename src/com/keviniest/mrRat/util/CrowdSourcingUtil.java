package com.keviniest.mrRat.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

import net.dv8tion.jda.api.entities.User;

public class CrowdSourcingUtil {

	public static Random random = new Random();
	public static FileWriter writer;
	public static String[] prep = { "on", "at", "inside", "in", "behind" };
	public static String[] conj = { "and", "or", "but", "nor" };

	private static String nounsFilePath = new File("").getAbsolutePath();
	private static String verbsFilePath = new File("").getAbsolutePath();
	private static String objsFilePath = new File("").getAbsolutePath();

	public static void init() {
		nounsFilePath = nounsFilePath.concat("\\resources\\MrRat\\data\\subjs.csv");
		verbsFilePath = verbsFilePath.concat("\\resources\\MrRat\\data\\verbs.csv\\");
		objsFilePath = objsFilePath.concat("\\resources\\MrRat\\data\\objs.csv");
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
				writeCSV(new File(nounsFilePath), word);
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println(user + " added : " + word);
		} else if(type.equalsIgnoreCase("verb")) {
			try {
				writeCSV(new File(verbsFilePath), word);
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println(user + " added : " + word);
		} else if(type.equalsIgnoreCase("obj")) {
			try {
				writeCSV(new File(objsFilePath), word);
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
		StringBuilder firstWord = new StringBuilder(readCSV(new File(nounsFilePath), random.nextInt(nounsFilePath.split(",").length)));
		String firstLetter = firstWord.toString().split("")[0].toUpperCase();
		firstWord = new StringBuilder(firstLetter + firstWord.delete(0, 1));

		String sentence = firstWord
				+ " "
				+ readCSV(new File(verbsFilePath), random.nextInt(verbsFilePath.split(",").length))
				+ " "
				+ readCSV(new File(objsFilePath), random.nextInt(objsFilePath.split(",").length));

		if(extended) {
			if(isPrep) {
				sentence = sentence
						+ " "
						+ prep[random.nextInt(prep.length)]
						+ " "
						+ readCSV(new File(objsFilePath), random.nextInt(objsFilePath.split(",").length));
			} else {
				sentence = sentence
						+ " "
						+ conj[random.nextInt(conj.length)]
						+ " "
						+ readCSV(new File(nounsFilePath), random.nextInt(nounsFilePath.split(",").length));
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