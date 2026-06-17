package buttons;

import java.awt.Color;

import situations.*;

/**
 * @author Leo Man, Jacky Phuong
 * 
 * */

/**
 * Represents an equals button in a calculator that performs the calculation
 * when pressed
 */
public class EqualsButton extends CalculatorButton {

	/**
	 * Constructs an EqualsButton with the specified label and situation
	 * 
	 * @param label     The label displayed on the button
	 * 
	 * @param situation The current situation of the calculator
	 */
	public EqualsButton(String label, Situation situation) {
		super(label, situation);
	}

	/**
	 * Handles the transition of the calculator based on its current state when the equal button is pressed
	 * */
	@Override
	public void transition() throws ArithmeticException {
		switch (situation.getstate()) {

		case Input1:
			// Maintain Input1 state and reset binary operator color
			situation.getBinOpButton().setBorder(Color.GRAY);
			situation.updateState(State.Input1);
			break;

		case Input2:
			 // Transition to HasResult state, perform calculation, reset binary operator color, and update display
			situation.getBinOpButton().setBorder(Color.GRAY);
			try {
				situation.getDisplay().setText(Integer.toString(situation.getBinOpButton().result(situation.getLeftOperand(),Integer.parseInt(situation.getDisplay().getText()))));
			}
			catch(ArithmeticException e) {
				 // If there's an arithmetic error, display "Error"
				situation.getDisplay().setText("Error");
			}
			situation.updateState(State.HasResult);
			break;

		case OpReady:
			// Maintain OpReady state
			situation.updateState(State.OpReady);
			break;
			
		case HasResult:
			// Maintain HasResult state
			situation.updateState(State.HasResult);
			break;
		}
	}
}
