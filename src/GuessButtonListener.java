
/**
 * GuessButtonListener.java
 * 
 * Purpose:
 * - Handles the "Guess" button clicks in the Word Puzzle Game.
 * - Processes user input from the text field and validates it before passing it to the game logic.
 * - Ensures only valid guesses (single letters) are processed.
 * 
 * Features:
 * - Captures user input from the text field.
 * - Validates the input to ensure it is a single letter.
 * - Calls the `checkGuess` method in `WordPuzzleGame` to handle the guess.
 * - Displays error messages for invalid inputs
 * 
 * @author Christopher OShea & Javier Ayala
 * @date 10 December 2024
 */


import java.awt.event.ActionListener;
import java.awt.event.ActionEvent; 

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
	        
	        // Ensure the input is a single letter
	        if (guess.length() == 1) 
	        {
	            char guessedCharacter = guess.charAt(0);
	            
	            // Checks if the guessed character is a valid letter (A-Z, a-z)
	            if (!Character.isLetter(guessedCharacter)) 
	            {
	                throw new IllegalArgumentException("Input must be a letter (A-Z).");
	            }

	            // Convert the character to lowercase or uppercase based on preference
	            guessedCharacter = Character.toLowerCase(guessedCharacter);

	            // Calls the checkGuess method to check the guess
	            game.checkGuess(guessedCharacter);

	            // Clears the input field after the guess
	            game.input.setText("");
	        }
	        else
	        {
	            // If the input is not a single letter, throw an exception
	            throw new IllegalArgumentException("Please enter a single letter.");
	        }
	    }
	    catch (IllegalArgumentException exception)
	    {
	        // Displays the error message in the message label
	        game.messageLabel.setText(exception.getMessage());
	    }
	}

}
			
			
