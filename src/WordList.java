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
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class WordList
{

	// Attribute: A list of words for the game
	private List<String> wordList;// Class wordList has a list of words

	/**
	 * Purpose: Constructor that takes in a file and uses the scanner tool to
	 * read the next word in the file
	 * 
	 **/
	public WordList(String fileName)
	{
		wordList = new ArrayList<>();//creates a new word list

		//creates a new scanner to read from a file
		try (Scanner scanner = new Scanner(new FileReader(fileName)))
		{
			//while the scanner can read the next line of the file its going to 
			while (scanner.hasNextLine())
			{
				//Reads each line and trims any extra spaces before adding to the list
				wordList.add(scanner.nextLine().trim());
			}
		}
		
		//If an error occurs this block will be executed
		catch (IOException e)
		{
			System.out.println(
					"Error reading word list file. Falling back to default words");
			
			//calls on this method for words in case there is an issue with the original file
			addDefaultWords();
		}
	}

	/*
	 * Purpose: Constructor that adds a number of default words in case the game
	 * doesn't read from the file correctly
	 */
	private void addDefaultWords()
	{
		// Adding some default words
		wordList.add("programming");
		wordList.add("java");
		wordList.add("cybersecurity");
		wordList.add("phone");
		wordList.add("internet");
		wordList.add("Remote");
		wordList.add("power");
		wordList.add("coding");
		wordList.add("methods");
		wordList.add("constructors");
		wordList.add("puzzle");
		wordList.add("Computer");
		wordList.add("Hardware");
		wordList.add("Mouseclick");
		wordList.add("Function");
		wordList.add("Database");
		wordList.add("Strategy");
		wordList.add("Adventure");
		wordList.add("Challenging");
		wordList.add("Mystery");
		wordList.add("Public");
	}

	/*
	 * Purpose: Method that gets a random word from the list
	 */
	public String getRandomWord()
	{
		//checks if the wordList is empty and prints out a message if it is
		if(wordList.isEmpty())
		{
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
