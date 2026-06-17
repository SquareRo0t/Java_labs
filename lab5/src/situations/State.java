package situations;

/**
 * Represents the different states of the calculator.
 */
public enum State {
	
	/**
	 *  The calculator is waiting for the first input
	 */
	Input1, 
	
	/**
	 * The first input is provided, and the calculator is ready for an operator
	 */
	OpReady, 
	
	/**
	 * An operator is selected, and the calculator is waiting for the second input
	 */
	Input2, 
	
	/**
	 *  Both inputs and the operator are provided, and the calculator has a result ready to display
	 */
	HasResult
}
