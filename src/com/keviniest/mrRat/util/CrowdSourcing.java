package com.keviniest.mrRat.util;

import java.util.ArrayList;
import java.util.Random;

import net.dv8tion.jda.api.entities.User;

public class CrowdSourcing {

	public static Random random = new Random();
	public static ArrayList<String> subj = new ArrayList<>();
	public static ArrayList<String> verbs = new ArrayList<>();
	public static ArrayList<String> objs = new ArrayList<>();
	public static String[] prep = { "on", "at", "inside", "in", "behind" };
	public static String[] conj = { "and", "or", "but", "nor" };

	public static int subjAdded = 0, verbAdded = 0, objAdded = 0;

	/**
	 * Adds a word to the arraylist.
	 *
	 * @param type type of the word (subj, verb, obj)
	 * @param word the actual word to be added
	 * @param user used for monitoring purpose. use .getAuthor() method
	 */
	public static void add(String type, String word, User user) {
		if(type.equalsIgnoreCase("subj")) {
			subj.add(subjAdded, word);
			System.out.println(user + " added : " + word);
			subjAdded++;
		} else if(type.equalsIgnoreCase("verb")) {
			verbs.add(verbAdded, word);
			System.out.println(user + " added : " + word);
			verbAdded++;
		} else if(type.equalsIgnoreCase("obj")) {
			objs.add(objAdded, word);
			System.out.println(user + " added : " + word);
			objAdded++;
		} else {
			System.err.println(" !!! Wrong type was given !!!");
		}
	}

	public static void addCSV(String type, String word, User user) {
		if(type.equalsIgnoreCase("subj")) {
			subj.add(subjAdded, word);
			System.out.println(user + " added : " + word);
			subjAdded++;
		} else if(type.equalsIgnoreCase("verb")) {
			verbs.add(verbAdded, word);
			System.out.println(user + " added : " + word);
			verbAdded++;
		} else if(type.equalsIgnoreCase("obj")) {
			objs.add(objAdded, word);
			System.out.println(user + " added : " + word);
			objAdded++;
		} else {
			System.err.println(" !!! Wrong type was given !!!");
		}
	}

	/**
	 * Removes a word from the arraylist. Note that removed word will be replaced with [removed].
	 * It is recommended to use set() method instead if you are trying change a word.
	 *
	 * @param type type of the word of the word trying to remove
	 * @param index position in arraylist where the word is in
	 * @param user used for monitoring purpose. use .getAuthor() method
	 */
	public static void remove(String type, int index, User user) {
		if(type.equalsIgnoreCase("subj")) {
			System.out.println(user + " removed : " + subj.get(index));
			subj.set(index, "[removed]");
		} else if(type.equalsIgnoreCase("verb")) {
			System.out.println(user + " removed : " + verbs.get(index));
			verbs.set(index, "[removed]");
		} else if(type.equalsIgnoreCase("obj")) {
			System.out.println(user + " removed : " + objs.get(index));
			objs.set(index, "[removed]");
		} else {
			System.err.println(" !!! Wrong type was given !!!");
		}
	}

	/**
	 * Changes word in given type and index to the word given.
	 *
	 * @param type type of the word of the word trying to set
	 * @param index position in arraylist where the word is in
	 * @param word the actual word to be changed
	 * @param user used for monitoring purpose. use .getAuthor() method
	 */
	public static void set(String type, int index, String word, User user) {
		if(type.equalsIgnoreCase("subj")) {
			subj.set(index, word);
			System.out.println(user + " set : " + subj.get(index));
		} else if(type.equalsIgnoreCase("verb")) {
			verbs.set(index, word);
			System.out.println(user + " set : " + verbs.get(index));
		} else if(type.equalsIgnoreCase("obj")) {
			objs.set(index, word);
			System.out.println(user + " set : " + objs.get(index));
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
	public String randomSentence() {
		boolean extended = random.nextBoolean();
		boolean isPrep = (extended) ? random.nextBoolean() : false;

		String sentence = subj.get(random.nextInt(subj.size()))
				+ " "
				+ verbs.get(random.nextInt(verbs.size()))
				+ " "
				+ objs.get(random.nextInt(objs.size()));

		if(extended) {
			if(isPrep) {
				sentence = sentence + " " + prep[random.nextInt(prep.length)] + " " + objs.get(random.nextInt(objs.size()));
			} else {
				sentence = sentence + " " + conj[random.nextInt(conj.length)] + " " + subj.get(random.nextInt(subj.size()));
			}
		}

		sentence = sentence + ".";
		return sentence;
	}
}