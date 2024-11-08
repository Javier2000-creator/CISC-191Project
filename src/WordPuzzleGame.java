import java.awt.BorderLayout;

import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;

public class WordPuzzleGame extends JFrame// a word puzzle game is a JFrame
{
	private JLabel displayLabel;// a word puzzle game has a displayLabel
	private char[] guessedWord;// a word puzzle game has many guessed words
	JLabel messageLabel;// a word puzzle game has a message label
	private String selectedWord;// a word puzzle game has many selected words
	JTextField input; // a word puzzle game has an input
	private WordList wordList;// a word puzzle game has a list of words
	private JPanel buttonPanel;
	private int wrongguesses;
	private static final int maximumattempts = 10;
	private JButton playAgainButton;
	private JButton hintButton;
	private int hintCount = 0;
	private static final int maxhints = 2;

	public WordPuzzleGame()
	{
		// sets the title of the game
		setTitle("Word Puzzle Game");

		// sets the size of the window
		setSize(600, 600);

		// Exit on close
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// set BorderLayout
		setLayout(new BorderLayout());

		wordList = new WordList();

		// select a new word to guess
		selectNewWord();

		// sets up display label
		displayLabel = new JLabel(getDisplayWord(), SwingConstants.CENTER);

		// sets the font, font type, and size
		displayLabel.setFont(new Font("Arial", Font.BOLD, 20));

		// message label
		messageLabel = new JLabel("", SwingConstants.CENTER);

		// input field for one character
		input = new JTextField(1);

		// creates a button called Guess
		JButton guessButton = new JButton("Guess");
		

		// adds action listener to the guess button
		guessButton.addActionListener(new GuessButtonListener(this));

		// takes input from the enter key to do the same function the guess
		// button does
		input.addActionListener(e -> guessButton.doClick());

		// Panel to hold input field and button
		JPanel inputPanel = new JPanel();

		// Add input field to panel
		inputPanel.add(input);

		playAgainButton = new JButton("Play Again");
		playAgainButton.addActionListener(e -> resetGame());
		playAgainButton.setVisible(true);

		hintButton = new JButton("Hint");
		hintButton.addActionListener(e -> provideHint());
		hintButton.setVisible(true);
		
		//adds buttons to the panel
		inputPanel.add(guessButton);
		inputPanel.add(hintButton);
		inputPanel.add(playAgainButton);

		// Adds components to the main frame
		buttonPanel = letterButtons();
		add(displayLabel, BorderLayout.CENTER); // Center the display label
		add(messageLabel, BorderLayout.NORTH); // Place message label at the top
		add(inputPanel, BorderLayout.SOUTH); // Place input panel at the bottom
		//add(playAgainButton, BorderLayout.WEST);
		//add(hintButton, BorderLayout.EAST);
		

		// makes the frame visible
		setVisible(true);

	}

	private JPanel letterButtons()
	{
		JPanel letterpanel = new JPanel();
		letterpanel.setLayout(new GridLayout(3, 9));

		for (char letter = 'A'; letter <= 'Z'; letter++)
		{
			final char thisletter = letter;
			PlayerButton mybutton = new PlayerButton(letter);
			mybutton.addWordListener(e -> buttonClickHandler(thisletter));
			letterpanel.add(mybutton);
		}

		return letterpanel;
	}

	private void buttonClickHandler(char guessedLetter)
	{
		checkGuess(guessedLetter);
	}

	/**
	 * Purpose: Retrieves the current state of the guessed word as a String
	 * 
	 * @return
	 */
	private String getDisplayWord()
	{
		return String.valueOf(guessedWord);
	}

	/**
	 * Purpose: Selects a random word from the list
	 */
	private void selectNewWord()
	{
		Random random = new Random();

		// Select a random word
		selectedWord = wordList.getRandomWord();
		guessedWord = new char[selectedWord.length()];
		for (int i = 0; i < guessedWord.length; i++)
		{
			guessedWord[i] = '_'; // Initialize with underscores
		}
	}

	private void resetGame()
	{
		wrongguesses = 0;
		selectNewWord();
		displayLabel.setText(getDisplayWord());
		messageLabel.setText("");
		playAgainButton.setVisible(true);
	}

	/**
	 * Purpose: Checks the user's guess
	 * 
	 * @param guessedChar
	 */
	public void checkGuess(char guessedChar)
	{
		boolean correctGuess = false;

		// Check if guessed character is in the selected word
		for (int i = 0; i < selectedWord.length(); i++)
		{
			if (selectedWord.charAt(i) == guessedChar)
			{
				guessedWord[i] = guessedChar; // Update guessed word
				correctGuess = true;
			}
		}

		// will display correct if the guess was right
		if (correctGuess)
		{
			messageLabel.setText("Correct");
		}
		// will display try again if the guess was wrong
		else
		{
			wrongguesses++;
			messageLabel.setText("Try again! Incorrect Guesses" + wrongguesses);
		}

		// updates the display word
		displayLabel.setText(getDisplayWord());

		// Check for win or lose
		if (new String(guessedWord).equals(selectedWord))
		{
			messageLabel.setText("You win! The word was: " + selectedWord);
		}

		else if (wrongguesses >= maximumattempts)
		{
			messageLabel.setText("You lose! The word was: " + selectedWord);
			playAgainButton.setVisible(true);
		}
	}

	private void provideHint()
	{
		boolean hintGiven = false;
		
		
		if(hintCount >= maxhints)
		{
			messageLabel.setText("Maximum amount of hints reached!");
	        hintButton.setEnabled(false); // Disables the hint button after 2 hints
	        return;
		}
		
		for (int i = 0; i < selectedWord.length(); i++)
		{
			if (guessedWord[i] == '_')
			{ // Find an unguessed letter
				guessedWord[i] = selectedWord.charAt(i); // Reveal the letter
				hintGiven = true;
				break; // Exit the loop after revealing one letter
			}
		}

		// If a hint was given, update the display
		if (hintGiven)
		{
			hintCount++;
			messageLabel.setText("Here's a hint!");
			displayLabel.setText(getDisplayWord());
		}
		else
		{
			// If no more hints can be given (e.g., the word is fully guessed)
			messageLabel.setText("No more hints available.");
		}
		
		if(hintCount >= maxhints)
		{
			hintButton.setEnabled(false);
		}
	}


	/**
	 * Purpose: Main method of the game that creates an instance of the game
	 * 
	 * @param args
	 */
	public static void main(String[] args)
	{
		new WordPuzzleGame();
	}
}
