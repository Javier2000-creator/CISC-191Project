import java.awt.BorderLayout;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.Random;

public class WordPuzzleGame extends JFrame// a word puzzle game is a JFrame
{
	private JLabel displayLabel;// a word puzzle game has a displayLabel
	private char[] guessedWord;// a word puzzle game has many guessed words
	protected JLabel messageLabel;// a word puzzle game has a message label
	private String selectedWord;// a word puzzle game has many selected words
	protected JTextField input; // a word puzzle game has an input
	private WordList wordList;// a word puzzle game has a list of words
	private JPanel buttonPanel;// a word puzzle game has a buttonPanel
	private int wrongguesses;// a word puzzle game has many wrong guesses
	private static final int maximumattempts = 10;// a word puzzle game has a
													// set amount of maximum
													// attempts
	private JButton playAgainButton;// a word puzzle game has a play again
									// button
	private JButton hintButton;// a word puzzle game has a hint button
	private int hintCount = 0;// a word puzzle game has a hint count
	private static final int maxhints = 2;// a word puzzle game has a max hint
											// number
	private JLabel timerLabel;
	private GameTimer gameTimer;

	public WordPuzzleGame(String wordFile)
	{
		// sets the title of the game
		setTitle("Word Puzzle Game");

		// sets the size of the window
		setSize(600, 600);

		// Exit on close
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// set BorderLayout
		setLayout(new BorderLayout());

		//creates a new variable that takes a wordFile(txt file) to read input from
		wordList = new WordList(wordFile);

		// select a new word to guess
		selectNewWord();

		// sets up display label
		displayLabel = new JLabel(getDisplayWord(), SwingConstants.CENTER);

		// sets the font, font type, and size
		displayLabel.setFont(new Font("Arial", Font.BOLD, 20));

		// message label
		messageLabel = new JLabel("", SwingConstants.CENTER);
		
		timerLabel = new JLabel("Time remaining: 30 seconds", SwingConstants.CENTER);
		
		gameTimer = new GameTimer(60, timerLabel, this);
		gameTimer.start();

		
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

		// adds buttons to the panel
		inputPanel.add(guessButton);
		inputPanel.add(hintButton);
		inputPanel.add(playAgainButton);

		// Adds components to the main frame
		buttonPanel = letterButtons();
		add(displayLabel, BorderLayout.CENTER); // Center the display label
		add(messageLabel, BorderLayout.NORTH); // Place message label at the top
		add(inputPanel, BorderLayout.SOUTH); // Place input panel at the bottom
		add(timerLabel, BorderLayout.EAST);

		// makes the frame visible
		setVisible(true);

	}

	/**
	 * Purpose: Method to create a panel of letter buttons (A-Z) for the user to
	 * click and guess letters
	 * 
	 * @return
	 */
	private JPanel letterButtons()
	{
		// Creates a new JPanel to hold the buttons
		JPanel letterpanel = new JPanel();

		// Sets the layout of the panel to a 3x9 grid (3 rows and 9 columns)
		letterpanel.setLayout(new GridLayout(3, 9));

		// Loop through all the letters from 'A' to 'Z'
		for (char letter = 'A'; letter <= 'Z'; letter++)
		{
			// Assigns the current letter to a final variable for use inside the
			// event handler
			final char thisletter = letter;

			// Creates a new PlayerButton for the current letter
			PlayerButton mybutton = new PlayerButton(letter);

			// Adds an action listener to the button that will trigger the
			// buttonClickHandler when clicked
			mybutton.addWordListener(e -> buttonClickHandler(thisletter));

			// Adds the button to the letter panel
			letterpanel.add(mybutton);
		}

		// Return the populated panel with all the letter buttons
		return letterpanel;
	}

	/**
	 * Purpose: Checks if the letter that the player guessed is part of the word
	 * they have to guess
	 * 
	 * @param guessedLetter
	 */
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

	/**
	 * Purpose: Resets the whole game
	 */
	private void resetGame()
	{
		wrongguesses = 0;
		guessedWord = new char[selectedWord.length()]; 
	    Arrays.fill(guessedWord, '_');  

		hintCount = 0;
		hintButton.setEnabled(true);
		selectNewWord();
		displayLabel.setText(getDisplayWord());
		messageLabel.setText("");
		playAgainButton.setVisible(true);
		gameTimer.reset(60);
		gameTimer.start();
	}

	/**
	 * Purpose: Checks the user's guess
	 * 
	 * @param guessedChar
	 */
	public void checkGuess(char guessedChar)
	{
		guessedChar = Character.toLowerCase(guessedChar);
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
			Timer winTimer = new Timer(1000, e -> resetGame());
	        winTimer.setRepeats(false);
	        winTimer.start();
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

		if (hintCount >= maxhints)
		{
			messageLabel.setText("Maximum amount of hints reached!");
			hintButton.setEnabled(false); // Disables the hint button after 2
											// hints
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

		if (hintCount >= maxhints)
		{
			hintButton.setEnabled(false);
		}
	}
	
	public void endGame(String message)
	{
		input.setEnabled(false);
		messageLabel.setText(message);
		gameTimer.stop();
	}

	/**
	 * Purpose: Main method of the game that creates an instance of the game
	 * 
	 * @param args
	 */
	public static void main(String[] args)
	{
		String wordFile = "WordList.txt";
		new WordPuzzleGame(wordFile);
	}
}
