import java.awt.BorderLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class WordPuzzleGame extends JFrame
{
	private JLabel displayLabel;
	private char[] guessedWord;
	private JLabel messageLabel;

	public WordPuzzleGame()
	{
		setTitle("Word Puzzle Game");
		setSize(600,600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		
		selectNewWord();
		
		displayLabel = new JLabel(getDisplayWord(), SwingConstants.CENTER);
		displayLabel.setFont(new Font("Arial", Font.BOLD, 20));
		messageLabel = new JLabel("", SwingConstants.CENTER);
	}

	private String getDisplayWord()
	{
		return String.valueOf(guessedWord);
	}
}
