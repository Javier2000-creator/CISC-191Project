import java.awt.event.ActionListener; 
import javax.swing.JButton;
/**
 * PlayerButton.java
 * 
 * Purpose:
 * - Represents a clickable button for a specific letter in the Word Puzzle Game.
 * - Extends JButton to include functionality for associating a letter with the button.
 * 
 * Features:
 * - Stores the letter it represents.
 * - Allows adding an ActionListener for user interaction.
 * 
 * @author Christopher OShea & Javier Ayala
 * @date 10 December 2024
 */

public class PlayerButton extends JButton //A PlayerButton represents a clickable letter button in the game. 
{
	private char letter;//a PlayerButton has a JButton 
	
	/**
	 * Purpose: Constructor that takes a character and initializes the button
	 * @param letter
	 */
	public PlayerButton(char letter)
	{
		super(String.valueOf(letter)); 
		this.letter = letter;
	}
	
	/**
	 * Purpose: Returns the letter
	 * @return
	 */
	public char getLetter()
	{
		return letter;
	}
	
	/**
	 * Purpose: Adds the ActionListener to the button
	 * @param listener
	 */
	public void addWordListener(ActionListener listener)
	{
		this.addActionListener(listener);
	}
}
