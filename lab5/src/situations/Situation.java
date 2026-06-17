package situations;

import javax.swing.JLabel;
import buttons.*;

/**
 * Represents the current situation of the calculator, including the state,
 * display, binary operator, and left operand
 */
public class Situation {

	// Current state of the calculator
	State state = State.Input1; // initial state

	// Where numbers and results are shown
	JLabel display;

	// The current binary operator selected by the user
	BinOpButton binaryOperator;

	// The left operand used in binary operations
	int leftOperand;

	/**
	 * Constructs a Situation object with the specified display
	 * 
	 * @param display The label where numbers and results are shown
	 */
	public Situation(JLabel display) {
		this.display = display;
	}

	/**
	 * Retrieves the current value displayed on the calculator
	 * 
	 * @return The value displayed on the calculator
	 */
	public JLabel getDisplay() {
		return this.display;
	}

	/**
	 * Sets the left operand to the specified value
	 * 
	 * @param a The value to set as the left operand
	 */
	public void setLeftOperand(int a) {
		this.leftOperand = a;
	}

	/**
	 * Retrieves the left operand used in binary operations
	 * 
	 * @return The left operand
	 */
	public int getLeftOperand() {
		return this.leftOperand;
	}

	/**
	 * Sets the binary operator to the specified button
	 * 
	 * @param binaryOperator The binary operator button
	 */
	public void setBinaryOperator(BinOpButton binaryOperator) {
		this.binaryOperator = binaryOperator;
	}

	/**
	 * Retrieves the current binary operator selected by the user
	 * 
	 * @return The binary operator button
	 */
	public BinOpButton getBinOpButton() {
		return binaryOperator;
	}

	/**
	 * Retrieves the current state of he calculator
	 * 
	 * @return The current state
	 */
	public State getstate() {
		return state;
	}

	/**
	 * Resets the state of the calculator to the initial state
	 */
	public void resetState() {
		this.state = State.Input1;
	}

	/**
	 * Updates the state of the calculator to the specified new state
	 * 
	 * @param newState The new state to set
	 */
	public void updateState(State newState) {
		this.state = newState;
	}
}
