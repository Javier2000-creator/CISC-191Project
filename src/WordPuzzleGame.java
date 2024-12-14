
/**
 * WordPuzzleGame.java
 * 
 * Purpose:
 * - Implements the main logic and GUI for the Word Puzzle Game.
 * - Manages user input, game state, and interactions with the word list and
 * timer.
 * 
 * Features:
 * - Displays the guessed word and updates as players guess letters.
 * - Integrates hints, a timer, and round-based gameplay.
 * - Handles game state transitions (win, lose, play again).
 * 
 * @author Christopher OShea & Javier Ayala
 * @date 10 December 2024
 */
import java.awt.BorderLayout;
import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class WordPuzzleGame extends JFrame// a word puzzle game is a JFrame
{
	private JLabel displayLabel;// a WordPuzzleGame has a displayLabel
	private char[] guessedWord;// a WordPuzzleGame has many guessed words
	protected JLabel messageLabel;// a WordPuzzleGame has a message label
	private String selectedWord;// a WordPuzzleGame has many selected words
	protected JTextField input; // a WordPuzzleGame has an input
	private WordList wordList;// a WordPuzzleGame has a list of words
	private JPanel buttonPanel;// a WordPuzzleGame has a buttonPanel
	private int wrongGuesses;// a WordPuzzleGame has many wrong guesses
	private static final int maximumattempts = 10;// a WordPuzzleGame has a
													// set amount of maximum
													// attempts
	private JButton playAgainButton;// a WordPuzzleGame has a play again
									// button
	private JButton hintButton;// a WordPuzzleGame has a hint button
	private int hintCount;// a WordPuzzleGame has a hint count
	private static final int maxhints = 2;// a WordPuzzleGame has a max hint
											// number
	private JLabel timerLabel;// a WordPuzzleGame has a timer label
	private GameTimer gameTimer;// a WordPuzzleGame has a game timer

	/**
	 * Purpose: Constructor that will take a word file and read off words from
	 * the file for the game. This constructor also holds the necessary info for
	 * the game's mechanics like the layout, buttons, etc.
	 * 
	 * @param wordFile
	 */
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

		// creates a new variable that takes a wordFile(txt file) to read input
		// from
		wordList = new WordList(wordFile);

		// select a new word to guess
		selectNewWord();

		// sets up display label
		displayLabel = new JLabel(getDisplayWord(), SwingConstants.CENTER);

		// sets the font, font type, and size
		displayLabel.setFont(new Font("Arial", Font.BOLD, 20));

		// message label
		messageLabel = new JLabel("", SwingConstants.CENTER);

		timerLabel = new JLabel("Time remaining: 60 seconds",
				SwingConstants.CENTER);

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

		hintButton = new JButton("Hint");
		hintButton.addActionListener(e -> provideHint());
		hintButton.setVisible(true);

		playAgainButton = new JButton("Play Again");
		playAgainButton.addActionListener(e -> resetGame());
		playAgainButton.setVisible(true);

		// Panel to hold input field and button
		JPanel inputPanel = new JPanel();

		// Add input field to panel
		inputPanel.add(input);
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
		// checks if the guessed word is null and returns an empty string if it
		// is
		if (guessedWord == null)
		{
			return "";
		}

		// else it returns and shows the guessed word if it is correct
		return String.valueOf(guessedWord);
	}

	/**
	 * Purpose: Selects a random word from the list
	 */
	private void selectNewWord()
	{
		// Select a random word
		selectedWord = wordList.getRandomWord();
		guessedWord = new char[selectedWord.length()];
		for (int i = 0; i < guessedWord.length; i++)
		{
			guessedWord[i] = '_'; // Initialize with underscores
		}

		hintCount = 0;

		// hintButton.setEnabled(true);
	}

	/**
	 * Purpose: This constructor provides a hint to the player, which is a
	 * maximum of two hints per word.
	 */
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

		for (int i = 0; i < selectedWord.length() && !hintGiven; i++)
		{
			if (guessedWord[i] == '_')
			{ // Find an unguessed letter
				guessedWord[i] = selectedWord.charAt(i); // Reveal the letter
				hintGiven = true; // Set flag to stop further iterations
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

		// if the number of hints used is greater than the number of hints given
		// then it disables the hint button
		if (hintCount >= maxhints)
		{
			hintButton.setEnabled(false);
		}
	}

	/**
	 * Purpose: Resets the whole game
	 */
	private void resetGame()
	{
		wrongGuesses = 0;//initializes the number of wrong guesses to 0
		selectNewWord();//calls the method to select a new word
		displayLabel.setText(getDisplayWord());//displays the word
		messageLabel.setText("");
		playAgainButton.setVisible(true);//sets the play again button to be visible 
		gameTimer.reset(60);//resets the game timer to 60 seconds
		gameTimer.start();//starts the game timer
	}

	/**
	 * Purpose: Checks the user's guess
	 * 
	 * @param guessedChar
	 */
	public void checkGuess(char guessedChar)
	{
		// makes sure that the letter(whether it is lower case or upper case) is correct when guessed
		guessedChar = Character.toLowerCase(guessedChar);
		boolean correctGuess = false;

		// Check if guessed character is in the selected word
		for (int i = 0; i < selectedWord.length(); i++)
		{
			if (Character.toLowerCase(selectedWord.charAt(i)) == guessedChar)
			{
				guessedWord[i] = selectedWord.charAt(i); // Update guessed word
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
			wrongGuesses++;
			messageLabel.setText("Try again! Incorrect Guesses" + wrongGuesses);
		}

		// updates the display word
		displayLabel.setText(getDisplayWord());

		// Check for a win or loss
		if (new String(guessedWord).equals(selectedWord))
		{
			//prints out a message if the guessed word is correct
			messageLabel.setText("You win! The word was: " + selectedWord);

			//resets the hint count to 0 every time a word is guessed correctly
			hintCount = 0;
			
			//enables the hint button
			hintButton.setEnabled(true);
			
			//selects a new word
			selectNewWord();
			displayLabel.setText(getDisplayWord());
			messageLabel.setText("");
		}

		else if (wrongGuesses >= maximumattempts)
		{
			//prints out a message after the maximum amount of attempts are used without correctly guessing the word
			messageLabel.setText("You lose! The word was: " + selectedWord);
			playAgainButton.setVisible(true);
		}
	}

	/**
	 * Purpose: Ends the game and leaves a message at the end of the game.
	 */
	public void endGame(String message)
	{
		input.setEnabled(false);//
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
