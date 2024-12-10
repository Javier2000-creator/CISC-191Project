/**
 * PlayerButton.java
 * 
 * Purpose:
 * - Represents a clickable button for a specific letter in the Word Puzzle Game.
 * - Extends JButton to associate a letter with the button.
 * 
 * Features:
 * - Stores the letter it represents.
 * - Provides a method to retrieve the letter.
 * - Allows adding an ActionListener for user interaction.
 * 
 * Author: Christopher OShea & Javier Ayala
 * Date: 10 December 2024
 */
import javax.swing.*;
import java.awt.event.ActionListener;

public class PlayerButton extends JButton {

    private char letter; // The letter this button represents

    /**
     * Constructor for PlayerButton.
     * 
     * @param letter The letter this button represents.
     */
    public PlayerButton(char letter) {
        super(String.valueOf(letter)); // Set the button's text to the letter
        this.letter = letter;
    }

    /**
     * Gets the letter represented by this button.
     * 
     * @return The letter represented by the button.
     */
    public char getLetter() {
        return letter;
    }

    /**
     * Adds an ActionListener to the button.
     * 
     * @param listener The ActionListener to associate with the button.
     */
    public void addWordListener(ActionListener listener) {
        this.addActionListener(listener);
    }
}
