import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;

public class GameTimer {
    private int duration;
    private int timeRemaining;
    private Timer timer;
    private JLabel timerLabel;
    private WordPuzzleGame game; // Reference to main game class

    public GameTimer(int duration, JLabel timerLabel, WordPuzzleGame game) {
        this.duration = duration;
        this.timeRemaining = duration;
        this.timerLabel = timerLabel;
        this.game = game;
    }

    // Start the timer
    public void start() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (timeRemaining > 0) {
                    timeRemaining--;
                    timerLabel.setText("Time remaining: " + timeRemaining + "s");
                } else {
                    timer.cancel();
                    game.endGame("Time's up!"); // Notify game that time's up
                }
            }
        }, 1000, 1000);
    }

    // Stop the timer
    public void stop() {
        if (timer != null) {
            timer.cancel();
        }
    }

    // Restart the timer for a new round
    public void restart() {
        stop();
        this.timeRemaining = duration;
        start();
    }
}
