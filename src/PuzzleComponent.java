import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;


//Purpose: Puzzle Component that has 
public abstract class PuzzleComponent extends JButton
{
	protected JLabel displayLabel;
	protected JLabel messageLabel;
	protected JTextField input;

	public PuzzleComponent()
	{
		displayLabel = new JLabel();
		messageLabel = new JLabel();
		input = new JTextField();

	}

	public abstract void initializeComponents();

	public void setUpLayout(JFrame frame)
	{
		frame.setLayout(new BorderLayout());
		frame.add(displayLabel, BorderLayout.CENTER);
		frame.add(messageLabel, BorderLayout.NORTH);
	}
}
