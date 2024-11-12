
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameTimer
{
	private int timeRemaining; 
	private JLabel timerLabel; 
	private WordPuzzleGame game; 
	private Timer timer; 


	public GameTimer(int initialTime, JLabel timerLabel, WordPuzzleGame game)
	{
		this.timeRemaining = initialTime; // Set the initial time for the game
		this.timerLabel = timerLabel; // The label to display the timer
		this.game = game; // Reference to the main game

		// Timer action to update the label every second
		ActionListener updateTimer = new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				// Decrease the time remaining
				timeRemaining--;

				// Update the timer label
				timerLabel.setText(
						"Time remaining: " + timeRemaining + " seconds");

				// If the timer reaches zero, stop it and trigger game over
				if (timeRemaining <= 0)
				{
					timer.stop();
					game.endGame("Time's up! You lose.");
				}
			}
		};

		// Create the Swing Timer to call the updateTimer every second (1000
		// milliseconds)
		timer = new Timer(1000, updateTimer);
	}

	
	public void start()
	{
		timer.start();
	}


	public void stop()
	{
		timer.stop();
	}


	public void reset(int newTime)
	{
		timeRemaining = newTime;
		timerLabel.setText("Time remaining: " + timeRemaining + " seconds");
	}
}
