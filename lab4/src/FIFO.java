
import java.util.ArrayList;
import java.util.NoSuchElementException;

/*
 *  FIFO (First in First Out) queue implementation
 */

public class FIFO implements Queue {

	// Class variables
	private ArrayList<Object> elements; // ArrayList to store elements of the queue
	private int maxSize; // Maximum size of the queue

	/*
	 * Constructs a new FIFO queue with initial capacity
	 */
	public FIFO() {
		elements = new ArrayList<>(); // Initialize the ArrayList to store elements
		maxSize = 0; // Initialize the maximum size to zero
	}

	// This method returns the number of elements in this queue.
	public int size() {
		return elements.size();
	}

	// This method returns the maximum size this queue has had since it was created.
	public int maxSize() {
		return maxSize;
	}

	/*
	 * This method returns true if, and only if, the size of this queue is 0.
	 * Otherwise, false is returned.
	 */
	public boolean isEmpty() {
		return size() == 0;
	}

	/*
	 * This method returns the first element in the queue. If the queue is empty, a
	 * NoSuchElementException is thrown.
	 */
	public Object first() {

		if (this.isEmpty()) {

			throw new NoSuchElementException();
		}
		return elements.get(0);
	}

	/*
	 * This method throws a ClassCastException if f is not of the same type as this
	 * class. This method returns true if, and only if, all the following holds:
	 * this and f have the same size. For every position i in this queue: – If the
	 * element at position i is null, the corresponding element at position i in f
	 * also is null. – If the element at position i instead is a reference to an
	 * object obj1, the corresponding element at position i in f is also a reference
	 * to an object obj2, and obj1.equals(obj2) is true. Otherwise, this method
	 * returns false. In particular, it does not throw any exception, such as a
	 * ClassCastException.
	 */

	public boolean equals(Object f) {

		// Check if the given object is an instance of FIFO
		if (!(f instanceof FIFO)) {
			throw new ClassCastException();
		}

		// Cast the given object to FIFO
		FIFO other = (FIFO) f; // 'f' is casted to FIFO and assigned to 'other'

		// Check if the sizes of both queues are equal
		if (this.size() != other.size()) {
			return false;
		}

		// Iterate through each element of the queue
		for (int i = 0; i < size(); i++) {

			// Check for null elements in both queues
			if (this.elements.get(i) == null) { // access to current instance of FIFO class
				if (other.elements.get(i) == null) { // access to object being compared to current instance of FIFO
														// class
					// If both elements are null, continue to the next iteration
					continue;

				} else {
					// If one element is null and the other is not, queues are not equal
					return false;
				}
				// Check for null elements in both queues
			} else if (other.elements.get(i) == null) {
				if (this.elements.get(i) == null) {
					// If both elements are null, continue to the next iteration
					continue;

				} else {
					// If one element is null and the other is not, queues are not equal
					return false;
				}

			} else {
				// If both elements are not null, compare them for equality
				if (this.elements.get(i).equals(other.elements.get(i))) {

					// If elements are equal, continue to the next iteration
					continue;
				} else {
					// If elements are not equal, queues are not equal
					return false;
				}
			}
		}
		// If all elements are equal, queues are equal
		return true;
	}

	/*
	 * This method returns a string beginning with "Queue: " followed by the
	 * following, for each element elem in the queue Note that the elements must be
	 * listed in order. Note that definition forces the string to end with a
	 * whitespace.
	 */

	public String toString() {

		// Initialize the result string variable with "Queue"
		String result = "Queue: ";

		// Iterate through each element of the queue
		for (int i = 0; i < size(); i++) {

			// Retrieve the element at index i
			Object element = elements.get(i);

			// Convert the element to a string representation and append it to the result
			// string
			result = result + "(" + String.valueOf(element) + ") ";
		}
		// Return the final string representation of the queue
		return result;
	}

	// This method adds the object item to the end of the queue.
	public void add(Object item) {

		// Add the item to the end of the queue
		elements.add(item);

		// Check if the current size if the queue exceeds the maximum size
		if (elements.size() > maxSize) {

			// Update the maximum size of the current size if necessary
			maxSize = elements.size();
		}
	}

	/*
	 * This method removes the first element from the queue. If the queue is empty,
	 * a NoSuchElementException is thrown.
	 */
	public void removeFirst() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		elements.remove(0);
	}

}
