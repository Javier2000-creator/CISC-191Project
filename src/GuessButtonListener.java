import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

public class GuessButtonListener implements ActionListener
{
	private WordPuzzleGame game;
	
	public GuessButtonListener(WordPuzzleGame game)
	{
		this.game = game;
	}	
	@Override
    public void actionPerformed(ActionEvent e) {
        String guess = game.input.getText(); // Access the input field from the game
        if (guess.length() == 1) {
            char guessedChar = guess.charAt(0);
            game.checkGuess(guessedChar); // Check the guess using the game method
            game.input.setText(""); // Clear the input field
        }
    }
}
	