import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
/**
 * WordList.java
 * 
 * Purpose:
 * - Manages the list of words used in the Word Puzzle Game.
 * - Handles loading words from a file and providing random word selection.
 * 
 * Features:
 * - Loads words from a file or adds default words if the file cannot be read.
 * - Selects a random word from the list.
 * - Prints all words (for testing purposes).
 * 
 * @author Christopher OShea & Javier Ayala
 * @date 10 December 2024
 */

public class WordList
{

	// Attribute: A list of words for the game
	private List<String> wordList;

	/*
	 * Purpose: Constructor that initializes the word list with some default words
	 */
	public WordList(String fileName)
	{
		wordList = new ArrayList<>();

		try (Scanner scanner = new Scanner(
				new FileReader(fileName)))
		{
			while (scanner.hasNextLine()) {
				wordList.add(scanner.nextLine().trim());
			}
		}

		catch (IOException e)
		{
			System.err.println(
					"Error reading word list file. Falling back to default words");
			addDefaultWords(); // Fallback to default words
		}
	}

	/*
	 * Purpose: Constructor that adds a number of default words in case the game doesn't read from the file correctly
	 */
	private void addDefaultWords()
	{
		// Adding some default words
		wordList.add("programming");
		wordList.add("java");
		wordList.add("cybersecurity");
		wordList.add("phone");
		wordList.add("internet");
	}

	/*
	 * Purpose: Method that gets a random word from the list
	 */
	public String getRandomWord()
	{
		if (wordList.isEmpty()) {
			throw new IllegalStateException("Word list is empty! Cannot select a word.");
		}
		Random random = new Random();
		int index = random.nextInt(wordList.size()); // Select a random index
		return wordList.get(index); // Return the word at that index
	}

	/*
	 * Purpose: Method to print all words in the list (for testing)
	 */
	public void printWordList()
	{
		for (String word : wordList)
		{
			System.out.println(word);
		}
	}

}
