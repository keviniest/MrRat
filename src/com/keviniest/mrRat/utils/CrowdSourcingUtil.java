package com.keviniest.mrRat.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;
import java.util.Scanner;

import net.dv8tion.jda.api.entities.User;

/**
 * Custom CSV file reader/writer and random sentence generation function
 */
public class CrowdSourcingUtil {

	private static final Random random = new Random();
	public static FileWriter writer;

	/**
	 * List of prepositions
	 */
	private static final String[] prep = {"on", "at", "inside", "in", "behind"};

	/**
	 * List of conjunctions
	 */
	private static final String[] conj = {"and", "or", "but", "nor"};

	private static File nouns;
	private static File verbs;
	private static File objs;

	/**
	 * Initializes folders then creates .csv files on the correct location.
	 * <p>
	 * File.createNewFile() returns true if file was successfully creates.
	 * Since we don't really need this, it's annotated with @SuppressWarnings annotation.
	 */
	@SuppressWarnings("ResultOfMethodCallIgnored")
	public static void init() {

		// Creates resources folder on same directory as the jar file
		try {
			Files.createDirectories(Paths.get("resources/MrRat/data"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Creates CSV files
		nouns = new File("resources/MrRat/data/subjs.csv");
		try {
			nouns.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}

		verbs = new File("resources/MrRat/data/verbs.csv");
		try {
			verbs.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}

		objs = new File("resources/MrRat/data/objs.csv");
		try {
			objs.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Saves words to corresponding CSV file
	 *
	 * @return true if word was successfully added, false is something went wrong
	 * @param type type of the word (subj, verb, obj)
	 * @param word the actual word to be added
	 * @param user used for monitoring purpose. use .getAuthor() method
	 */
	public static boolean add(String type, String word, User user) {
		boolean status = false;
		if (type.equalsIgnoreCase("subj")) {
			try {
				writeCSV(nouns, word);
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println(user + " added : " + word);
			status = true;
		} else if (type.equalsIgnoreCase("verb")) {
			try {
				writeCSV(verbs, word);
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println(user + " added : " + word);
			status = true;
		} else if (type.equalsIgnoreCase("obj")) {
			try {
				writeCSV(objs, word);
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println(user + " added : " + word);
			status = true;
		}
		return status;
	}

	/**
	 * Base sentence is first generated using noun + verb + object format.
	 * After that, there is a chance that the sentence will follow with a preposition or a conjunction.
	 * If it is followed by preposition, it would pick random word from objects, if it is conjunction,
	 * it would pick one random word from the subjects.
	 *
	 * @return Randomly generated sentence using logic above
	 * @throws FileNotFoundException When file is not found
	 */
	public static String randomSentence() throws FileNotFoundException {
		// Decide if the sentence is simple or not
		boolean extended = random.nextBoolean();

		// Decides if the sentence is followed by prepositional phrase, if not, it's followed by a conjunctional phrase
		boolean isPrep = extended && random.nextBoolean();

		// First word in sentence is capitalized logic
		// A random word get picked here
		StringBuilder firstWord = new StringBuilder(readCSV(nouns, random.nextInt(stringify(nouns).split(",").length)));

		// The word gets splitted into letter by letter and get put into an array
		// Then, the first item in the array (first letter of the word) gets capitalized
		// firstLetter is the capitalized first letter of the picked word.
		String firstLetter = firstWord.toString().split("")[0].toUpperCase();

		// The capitalized first letter gets appended to the start of the originally picked word w/o the first letter
		firstWord = new StringBuilder(firstLetter + firstWord.delete(0, 1));

		String sentence = firstWord
				+ " "
				// Randomly picked verb from the list
				+ readCSV(verbs, random.nextInt(stringify(verbs).split(",").length))
				+ " "
				// Randomly picked object from the list
				+ readCSV(objs, random.nextInt(stringify(objs).split(",").length));

		if (extended) {
			if (isPrep) {
				// Followed by prepositional phrase case
				sentence = sentence
						+ " "
						+ prep[random.nextInt(prep.length)]
						+ " "
						+ readCSV(objs, random.nextInt(stringify(objs).split(",").length));
			} else {
				// Followed by conjunctional phrase case
				sentence = sentence
						+ " "
						+ conj[random.nextInt(conj.length)]
						+ " "
						+ readCSV(nouns, random.nextInt(stringify(nouns).split(",").length));
			}
		}

		sentence = sentence + ".";
		return sentence;
	}

	/**
	 * Reads a word in given csv list index, in a given list of csv list.
	 *
	 * @param type  Type of the word (subj, verb, obj) (.csv file)
	 * @param index Index(position) of the word in the list
	 * @return A word in the given csv list, the word is picked by given index.
	 * @throws FileNotFoundException When file is not found
	 */
	private static String readCSV(File type, int index) throws FileNotFoundException {
		StringBuilder word = new StringBuilder();
		Scanner scan = new Scanner(type);
		while (scan.hasNextLine()) {
			word.append(scan.nextLine());
		}
		String[] wordList = word.toString().split(",");
		scan.close();
		return wordList[index];
	}

	/**
	 * Adds the given word to given csv list.
	 *
	 * @param type Type of the word (subj, verb, obj) (.csv file)
	 * @param word The Word to be written.
	 * @throws IOException When Something happens
	 */
	private static void writeCSV(File type, String word) throws IOException {
		writer = new FileWriter(type, true);
		writer.write(word + ",\n");
		writer.close();
	}

	/**
	 * Reads and returns a string which is entirety of a given csv list.
	 *
	 * @param type Type of the word (subj, verb, obj) (.csv file)
	 * @return entirety of given csv file
	 * @throws FileNotFoundException - When file is not found
	 */
	private static String stringify(File type) throws FileNotFoundException {
		StringBuilder word = new StringBuilder();
		Scanner scan = new Scanner(type);
		while (scan.hasNextLine()) {
			word.append(scan.nextLine());
		}
		return word.toString();
	}

}