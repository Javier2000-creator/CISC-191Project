import java.awt.BorderLayout;

import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;

public class WordPuzzleGame extends JFrame
{
	private JLabel displayLabel;
	private char[] guessedWord;
	private JLabel messageLabel;
	private String selectedWord;
	JTextField input;
	private List<String> wordList;

	public WordPuzzleGame()
	{
		setTitle("Word Puzzle Game");
		setSize(600, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());

		initializeWordList();
		selectNewWord();

		displayLabel = new JLabel(getDisplayWord(), SwingConstants.CENTER);
		displayLabel.setFont(new Font("Arial", Font.BOLD, 20));
		messageLabel = new JLabel("", SwingConstants.CENTER);

		input = new JTextField(1);
		JButton guessButton = new JButton("Guess");
		guessButton.addActionListener(new GuessButtonListener(this));

		JPanel inputPanel = new JPanel();
		inputPanel.add(input);
		inputPanel.add(guessButton);

		add(displayLabel, BorderLayout.CENTER);
		add(messageLabel, BorderLayout.NORTH);
		add(inputPanel, BorderLayout.SOUTH);

		setVisible(true);

	}

	private void initializeWordList()
	{
		wordList = new ArrayList<>();
		wordList.add("programming");
		wordList.add("java");
		wordList.add("cybersecurity");
		wordList.add("phone");
		wordList.add("internet");

	}

	private String getDisplayWord()
	{
		return String.valueOf(guessedWord);
	}

	private void selectNewWord()
	{
		Random random = new Random();
		selectedWord = wordList.get(random.nextInt(wordList.size())); // Select
																		// a
																		// random
																		// word
		guessedWord = new char[selectedWord.length()];
		for (int i = 0; i < guessedWord.length; i++)
		{
			guessedWord[i] = '_'; // Initialize with underscores
		}
	}

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

		if (correctGuess)
		{
			messageLabel.setText("Good guess!");
		}
		else
		{
			messageLabel.setText("Try again!");
		}

		displayLabel.setText(getDisplayWord());
	}

	public static void main(String[] args)
	{
		new WordPuzzleGame();
	}
}
