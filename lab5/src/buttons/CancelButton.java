package buttons;

import situations.*;

import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * @author Leo Man, Jacky Phuong
 * 
 */

/**
 * Represents a cancel button in a calculator that resets or cancels the current operation
 * */
public class CancelButton extends CalculatorButton {

	/**
	 * Constructs a CancelButton with the specified label and situation
	 * 
	 * @param label The label displayed on the button
	 * 
	 * @param situation The current situation of the calculator
	 */
	public CancelButton(String label, Situation situation) {
		super(label, situation);
	}

	/**
	 * Handles the transition of the calculator based on its current state when the
	 * cancel button is pressed
	 */
	@Override
	public void transition() {

		switch (situation.getstate()) {

		case Input1:
			 // Reset display to 0 and reset state to Input1
			situation.getDisplay().setText("0");
			situation.resetState();
			situation.getBinOpButton().setBorder(Color.GRAY);
			break;

		case Input2:
			 // Reset display to 0, reset state to Input1, and reset color of the binary operator button
			situation.getDisplay().setText("0");
			situation.resetState();
			situation.getBinOpButton().setBorder(Color.GRAY);
			break;

		case OpReady:
			// Reset display to 0, reset state to Input1, and reset color of the binary operator button
			situation.getDisplay().setText("0");
			situation.resetState();
			situation.getBinOpButton().setBorder(Color.GRAY);

		case HasResult:
			// Reset display to 0 and reset state to Input1
			situation.getDisplay().setText("0");
			situation.resetState();
			situation.getBinOpButton().setBorder(Color.GRAY);
			break;
		}
	}
}
