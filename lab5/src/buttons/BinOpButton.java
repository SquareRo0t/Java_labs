package buttons;

import general.*;
import situations.Situation;
import situations.State;

import java.awt.*;
import java.util.function.IntBinaryOperator;

/**
 * @author Leo Man, Jacky Phuong
 */
/**
 * Represents a button for binary operations in a calculator
 * 
 * 
 */

/** Implementation of the BinOpButton class */
public class BinOpButton extends CalculatorButton {

	/** The binary operation associated with this button */
	private IntBinaryOperator op;

	/**
	 * Constructs a BinOpButton with the specified label, situation, and binary
	 * operation.
	 * 
	 * @param label     The label displayed on the button
	 * 
	 * @param situation The Current situation of the calculator
	 * 
	 * @param op        The binary operation to be performed when this button is
	 *                  pressed
	 */
	public BinOpButton(String label, Situation situation, IntBinaryOperator op) {
		super(label, situation);
		this.op = op;
	}

	/**
	 * Calculates the result of applying the binary operation to the given operands
	 * 
	 * @param a The first operand
	 * 
	 * @param b The second operand
	 * 
	 * @return The result of the binary operation
	 */
	public int result(int a, int b) {
		return op.applyAsInt(a, b);
	}

	/**
	 * Handles the transition of the calculator based on its current state when this
	 * button is pressed
	 */
	@Override
	public void transition() {

		switch (situation.getstate()) {
		case Input1:
			// If in Input1 state, set the left operand, set binary operator, update state
			situation.setLeftOperand(Integer.parseInt(situation.getDisplay().getText()));
			this.setBorder(Color.RED); // Visual indication of button press
			situation.setBinaryOperator(this); // Set the binary operator associated with button
			situation.updateState(State.OpReady); // Transition to OpReady state
			break;
			
		case Input2:
			  // If in Input2 state, reset the display to 0 and maintain state
			this.setBorder(Color.RED); // Visual indication of button press
			situation.getDisplay().setText("0"); // Reset display
			situation.updateState(State.Input2); // Maintain Input2 state
			break;
			
		case OpReady:
			// If in OpReady state, update binary operator, maintain state
			situation.getBinOpButton().setBorder(Color.GRAY); // Reset border of previous operator button
			situation.setBinaryOperator(this);  // Set the binary operator associated with this button
			this.setBorder(Color.RED); // Visual indication of button press
			situation.updateState(State.OpReady); // Maintain OpReady state
			break;
			
		case HasResult:
			// If in HasResult state, set left operand, set binary operator, update state
			situation.getBinOpButton().setBorder(Color.GRAY); // Reset border of previous operator button
			situation.setLeftOperand(Integer.parseInt(situation.getDisplay().getText())); // Set left operand
			situation.setBinaryOperator(this); // Set the binary operator associated with this button
			this.setBorder(Color.RED); // Visual indication of button press
			situation.updateState(State.OpReady); // Transition to OpReady state
		}
	}
}
