import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

public class GuessButtonListener implements ActionListener// a
															// GuessButtonListener
															// is a Action
															// Listener
{
	private WordPuzzleGame game;// a WordPuzzleGame has a game

	/**
	 * Purpose: Constructor that takes a WordPuzzleGame instance
	 * 
	 * @param game
	 */
	public GuessButtonListener(WordPuzzleGame game) 
	{
		this.game = game;
	}

	/**
	 * Purpose: Constructor that creates a method to handle button clicks
	 */
	@Override
    public void actionPerformed(ActionEvent event)
	{
		try 
		{
			String guess = game.input.getText(); // Access the input field from the game
			
			if (guess.length() == 1) 
			{
				char guessedcharacter = guess.charAt(0);
				
				if(!Character.isLetter(guessedcharacter))
				{
					throw new IllegalArgumentException("Input must be a letter");
				}
				
				guessedcharacter = Character.toLowerCase(guessedcharacter);
				game.checkGuess(guessedcharacter);
				game.input.setText("");
			}
			else
			{
				throw new IllegalArgumentException("Please enter a letter");
			}
		}
		
		catch(IllegalArgumentException exception)
		{
			game.messageLabel.setText(exception.getMessage());
		}
	}
}
			
			
