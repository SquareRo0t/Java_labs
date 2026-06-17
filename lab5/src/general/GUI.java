package general;

import java.awt.*;
import javax.swing.*;
import java.util.function.IntBinaryOperator;

import buttons.*;
import situations.*;

/**
 * @author Leo Man, Jacky Phuong
 * 
 * */

/**
 * Represents the graphical user interface (GUI) of the calculator
 */
public class GUI extends JFrame {

	/** Panels for display and keypad */
	private JPanel canvas;

	/**
	 * The label used to display the input and output of the calculator.
	 */
	private JLabel display;

	/**
	 * The panel containing the keypad buttons of the calculator.
	 */
	private JPanel keyPad;

	/**
	 * Constructs the GUI for the calculator
	 */
	public GUI() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Create canvas
		canvas = new JPanel();
		canvas.setLayout(new GridBagLayout());
		canvas.setSize(1000, 1000);
		setContentPane(canvas);

		// Create display
		display = new JLabel("0");
		display.setFont(new Font("Arial", Font.PLAIN, 40));
		display.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		display.setHorizontalAlignment(SwingConstants.RIGHT);

		// Create an instance of Situation passing the display JLabel
		Situation situation = new Situation(this.display);
		
		// Add display to the canvas
		GridBagConstraints constraintsDisplay = new GridBagConstraints();
		constraintsDisplay.fill = GridBagConstraints.BOTH;
		constraintsDisplay.weightx = 1;
		constraintsDisplay.weighty = 1;
		constraintsDisplay.gridx = 0;
		constraintsDisplay.gridy = 0;
		constraintsDisplay.gridwidth = 1;
		constraintsDisplay.gridwidth = 1;
		canvas.add(display, constraintsDisplay);

		// Create keyPad
		keyPad = new JPanel();

		// Add keyPad to the canvas
		keyPad.setLayout(new GridLayout(4, 4));
		GridBagConstraints constraintsKeyPad = new GridBagConstraints();
		constraintsKeyPad.fill = GridBagConstraints.BOTH;
		constraintsKeyPad.weightx = 1;
		constraintsKeyPad.weighty = 1;
		constraintsKeyPad.gridx = 0;
		constraintsKeyPad.gridy = 4;
		constraintsKeyPad.gridwidth = 1;
		constraintsKeyPad.gridwidth = 4;
		this.canvas.add(this.keyPad, constraintsKeyPad);

		// Create and add buttons to the keyPad
		keyPad.add(new DigitButton("7", situation));
		keyPad.add(new DigitButton("8", situation));
		keyPad.add(new DigitButton("9", situation));
		keyPad.add(new BinOpButton("/", situation, new IntBinaryOperator() {
			@Override
			public int applyAsInt(int arg1, int arg2) {
				return arg1/arg2;
			}
		}));

		keyPad.add(new DigitButton("4", situation));
		keyPad.add(new DigitButton("5", situation));
		keyPad.add(new DigitButton("6", situation));
		keyPad.add(new BinOpButton("*", situation, new IntBinaryOperator() {
			@Override
			public int applyAsInt(int arg1, int arg2) {
				return arg1*arg2;
			}
		}));

		keyPad.add(new DigitButton("1", situation));
		keyPad.add(new DigitButton("2", situation));
		keyPad.add(new DigitButton("3", situation));
		keyPad.add(new BinOpButton("-", situation, new IntBinaryOperator() {
			@Override
			public int applyAsInt(int arg1, int arg2) {
				return arg1-arg2;
			}
		}));

		keyPad.add(new DigitButton("0", situation));
		keyPad.add(new EqualsButton("=", situation));
		keyPad.add(new CancelButton("C", situation));
		keyPad.add(new BinOpButton("+", situation, new IntBinaryOperator() {
			@Override
			public int applyAsInt(int arg1, int arg2) {
				return arg1+arg2;
			}
		}));

		// Pack and set the GUI visible
		pack();
		setVisible(true);
	}
}
