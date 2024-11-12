import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameTimer {
    private int timeRemaining; // in seconds
    private Timer timer;
    private JLabel timerLabel;

    public GameTimer(int seconds, JLabel timerLabel) {
        this.timeRemaining = seconds;
        this.timerLabel = timerLabel;
        
        // Define the action for each timer tick (every second)
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timeRemaining--;
                updateTimerLabel();
                
                // Check if time is up
                if (timeRemaining <= 0) {
                    timer.stop();
                    JOptionPane.showMessageDialog(null, "Time's up! You lose this round.");
                }
            }
        });
    }

    // Starts or resumes the timer
    public void start() {
        timer.start();
    }

    // Pauses the timer
    public void stop() {
        timer.stop();
    }

    // Resets the timer to initial value and stops it (only when pressing "Play Again")
    public void reset(int newTime) {
        timer.stop();
        this.timeRemaining = newTime;
        updateTimerLabel();
    }

    // Updates the timer label on the GUI
    private void updateTimerLabel() {
        timerLabel.setText("Time remaining: " + timeRemaining + " seconds");
    }
}
