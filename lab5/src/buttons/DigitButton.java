package buttons;

import situations.*;

/**
 * @author Leo Man, Jacky Phuong 
 * 
 * 
 */

/**
 * Represents a button for entering digits in a calculator
 * */
public class DigitButton extends CalculatorButton {

	/**
	 * Represents a button in the calculator keypad that corresponds to a digit.
	 *
	 * @param string    The string representation of the digit displayed on the button.
	 * @param situation The situation associated with this button.
	 */
	public DigitButton(String string, Situation situation) {
		super(string, situation);
	}

	/**
	 * Handles the transition of the calculator based on its current state when a
	 * digit is pressed
	 */
	@Override
	public void transition() {
		switch (situation.getstate()) {

		case Input1:
			// Append the pressed digit to the current display value
			if(situation.getDisplay().getText() == "0") {
				situation.getDisplay().setText(this.toString());
			}
			else {
				situation.getDisplay().setText(situation.getDisplay().getText() + this.toString());
			}
			situation.updateState(State.Input1);
			break;

		case Input2:
			// Append the pressed digit to the current display value
			if(situation.getDisplay().getText() == "0") {
				situation.getDisplay().setText(this.toString());
			}
			else {
				situation.getDisplay().setText(situation.getDisplay().getText() + this.toString());
			}
			situation.updateState(State.Input2);
			break;

		case OpReady:
			 // Transition to Input2 state, store left operand, and set display to the
            // pressed digit
			situation.getDisplay().setText(this.toString());
			situation.updateState(State.Input2);
			break;
			
		case HasResult:
			// Transition to Input1 state and set display to the pressed digit
			situation.getDisplay().setText(this.toString());
			situation.updateState(State.Input1);
			break;
		}
	}
}
