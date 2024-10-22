import java.awt.event.ActionListener;

import javax.swing.JButton;

public class PlayerButton extends JButton
{
	private char letter;
	
	public PlayerButton(char letter)
	{
		super(String.valueOf(letter));
		this.letter = letter;
	}
	
	public char getLetter()
	{
		return letter;
	}
	
	public void addWordListener(ActionListener listener)
	{
		this.addActionListener(listener);
	}
}
