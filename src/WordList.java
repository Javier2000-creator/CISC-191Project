import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WordList
{

	// Attribute: A list of words for the game
	private List<String> wordList;

	// Constructor: Initializes the word list with some default words
	public WordList(String fileName)
	{
		wordList = new ArrayList<>();

		try (BufferedReader reader = new BufferedReader(
				new FileReader(fileName)))
		{
			String line;
			while ((line = reader.readLine()) != null)
			{
				wordList.add(line.trim());
			}
		}

		catch (IOException e)
		{
			System.out.println(
					"Error reading word list file. Falling bacl to default words");
			addDefaultWords();
		}
	}

	private void addDefaultWords()
	{
		// Adding some default words
		wordList.add("programming");
		wordList.add("java");
		wordList.add("cybersecurity");
		wordList.add("phone");
		wordList.add("internet");
	}

	// Method: Get a random word from the list
	public String getRandomWord()
	{
		Random random = new Random();
		int index = random.nextInt(wordList.size()); // Select a random index
		return wordList.get(index); // Return the word at that index
	}

	// Optional: Method to print all words in the list (for testing)
	public void printWordList()
	{
		for (String word : wordList)
		{
			System.out.println(word);
		}
	}

}
