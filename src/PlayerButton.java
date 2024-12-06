import java.awt.event.ActionListener; 

import javax.swing.JButton;

public class PlayerButton extends JButton//PlayerButton is a JButton
{
	private char letter;//a PlayerButton has a letter
	
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
