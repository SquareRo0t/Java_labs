package lab2;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

@SuppressWarnings("serial")

public class MyArrayList<E> implements Serializable, Cloneable, Iterable<E>, Collection<E>, List<E>, RandomAccess {

	// ---------------------------------------------------------------

	public static void main(String[] args) {
		MyArrayList<String> strList = new MyArrayList<>();

		// Testa metoder här

		strList.add("A");
		strList.add("B");
		strList.add("C");
		strList.add("D");
		strList.add("E");
		strList.add("F");
		strList.add("G");
		System.out.println("List 1");
		printList(strList);

		strList.add("H");
		// strList.add(0, "H");
		// strList.remove(0);
		// strList.removeRange(0, 4);
		// strList.clear();
		System.out.println("List 2");
		printList(strList);

	}

	// ---------------------------------------------------------------

	// Variables
	private E[] array; // Used array to store elements of the list
	private int size; // Current number of elements in the list

	// Method to print the contents of the list
	private static void printList(MyArrayList<String> list) {

		// Prints the starting square bracket for the list
		System.out.print("[");

		// Loop through each element at index i
		for (int i = 0; i < list.size(); i++) {
			System.out.print(list.get(i));

			// If not the last element, will print a comma and space
			if (i < list.size() - 1) {
				System.out.print(", ");
			}
		}
		// Prints the ending square bracket for the list
		System.out.println("]");
	}

	// ---------------------------------------------------------------

	// Constructs an empty list with the specified initial capacity.
	public MyArrayList(int initialCapacity) {

		// If below zero throw an exception.
		if (initialCapacity < 0) {
			throw new IllegalArgumentException();
		}
		// Creates a new array, generic type E with specified initial capacity
		array = (E[]) new Object[initialCapacity];
	}

	// Constructs an empty list with an initial capacity of ten.
	public MyArrayList() {
		array = (E[]) new Object[10];
	}

	// -- 1 --

	// Returns the number of elements in this list.
	@Override
	public int size() {
		return size;
	}

	// Checks if the list is empty
	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	// Clear the list by removing all elements
	@Override
	public void clear() {
		size = 0;
	}
	// ---------------------------------------------------------------

	// -- 2 --
	/*
	 * Increases the capacity of this ArrayList instance, if necessary, to ensure
	 * that it can hold at least the number of elements specified by the minimum
	 * capacity argument.
	 */
	// Ensures that the internal array has enough capacity to accommodate at least
	// minCap elements
	public void ensureCapacity(int minCapacity) {

		// Check if the specified minCap exceeds the current Cap of the array
		if (minCapacity > array.length) {

			// calculate the new Cap as the new Maxvalue between minCap and the current Cap
			int newCapacity = Math.max(minCapacity, array.length);

			// Copy the elements of existing array to a new one with the new capacity
			reSize(newCapacity);
		}
	}

	/*
	 * Trims the capacity of this ArrayList instance to be the list's current size.
	 * An application can use this operation to minimize the storage of an ArrayList
	 * instance
	 */
	public void trimToSize() {

		// Check if the number of elements in the list is less than the current Cap of
		// Array
		if (size < array.length) {

			/*
			 * Copy the elements of existing array to a new one with the new capacity equal
			 * to the size of the list
			 */
			reSize(size);
		}
	}

	// Help method to resize the internal array
	private void reSize(int size) {

		// Create a new array of the specified to accommodate the resized list
		E[] newArray = (E[]) new Object[size];

		// Copy elements from the existing array to the new array
		for (int i = 0; i < array.length; i++) {
			newArray[i] = array[i];
		}

		// Update the reference to the internal array to point to the new array
		array = newArray;
	}
	// ---------------------------------------------------------------

	// -- 3 --
	/*
	 * Inserts the specified element at the specified position in this list. shifts
	 * the element currently at that position (if any) and any subsequent elements
	 * to the right (adds one to their indices)
	 */
	@Override
	public void add(int index, E element) {

		// Check if index is valid
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		}
		// Ensure that the internal array has enough capacity to accommodate the new
		// element
		ensureCapacity(size + 1);

		// Loops from the last elements towards the index where the new element is to be
		// inserted
		if (index < size()) {
			for (int i = size; i > index; i--) {

				// Shifts elements to the right to make space for the new element
				array[i] = array[i - 1];
			}
		}

		// Insert the new element at the specific index
		array[index] = element;

		// increment the size of the list
		size++;
	}

	// Appends the specified element to the end of this list.
	@Override
	public boolean add(E e) {

		/*
		 * Ensure that the internal array has enough capacity to accommodate the new
		 * element
		 */
		ensureCapacity(size + 1);

		// Add new element at the end of the list and increment the size
		array[size++] = e;

		// Return true to indicate that it was successful
		return true;
	}
	// ---------------------------------------------------------------

	// -- 4 --
	// Returns the element at the specified position in this list.
	@Override
	public E get(int index) {

		// Check if index is valid
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		// Return the element at the specific index
		return array[index];
	}

	/*
	 * Replaces the element at the specified position in this list with the
	 * specified element
	 */
	@Override
	public E set(int index, E element) {

		// Check if index is valid
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		// Store old value at specific index
		E oldValue = array[index];

		// Replace the element at the specific index with the specified element
		array[index] = element;

		// Return old value that was replaced
		return oldValue;
	}
	// ---------------------------------------------------------------

	// -- 5 --
	/*
	 * Removes the element at the specified position in this list. Shifts any
	 * subsequent elements to the left (subtracts one from their indices)
	 */
	@Override
	public E remove(int index) {

		// Check if index is valid
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		// Store the element to be removed
		E removedElement = array[index];

		for (int i = index; i < size() - 1; i++) {
			// Shift elements to the left to fill the gap left by the removed element
			array[i] = array[i + 1];
		}

		// Decrement the size of the list
		size--;
		return removedElement;
	}

	/*
	 * Removes from this list all of the elements whose index is between fromIndex,
	 * inclusive, and toIndex, exclusive. Shifts any succeeding elements to the left
	 * (reduces their index). This call shortens the list by (toIndex - fromIndex)
	 * elements. (If toIndex==fromIndex, this operation has no effect.)
	 */
	public void removeRange(int fromIndex, int toIndex) {

		// Check if range is valid
		if (fromIndex < 0 || toIndex > size || fromIndex > toIndex) {
			throw new IndexOutOfBoundsException();
		}
		// Calculates the number of elements to remove
		int numToRemove = toIndex - fromIndex;

		for (int i = toIndex; i < size; i++) {
			// Shift elements to the left to fill the gap left by the removed element
			array[i - numToRemove] = array[i];
		}

		// Adjust the size of the list
		size -= numToRemove;
	}
	// ---------------------------------------------------------------

	// -- 6 --
	/*
	 * Returns the index of the first occurrence of the specified element in this
	 * list, or -1 if this list does not contain the element
	 */
	@Override
	public int indexOf(Object o) {

		// Check if the object to find is null
		if (o == null) {
			for (int i = 0; i < size; i++) {

				// If element at index i is null, return its index
				if (array[i] == null) {
					return i;
				}
			}
		} else {
			for (int i = 0; i < size; i++) {

				// If the element at index i equals the specified object, return its index
				if (o.equals(array[i])) {
					return i;
				}
			}
		}
		// Return -1 if the element is not found
		return -1;
	}

	/*
	 * Removes the first occurrence of the specified element from this list, if it
	 * is present.
	 */
	@Override
	public boolean remove(Object o) {

		// Find the index of the element to remove
		int index = indexOf(o);

		// If the element is found
		if (index != -1) {

			// Remove the element at the index
			remove(index);
			return true;
		}
		return false;
	}

	// Returns true if this list contains the specified element.
	@Override
	public boolean contains(Object o) {

		// Check if the index of the element is not -1
		return indexOf(o) != -1;
	}
	// ---------------------------------------------------------------

	// -- 7 --

	// Returns a shallow copy of this ArrayList instance.
	@Override
	public Object clone() {
		/*
		 * Creates a new instance of MyArrayList with the same initial capacity as this
		 * list
		 */
		MyArrayList<E> cloneList = new MyArrayList<>(array.length);

		// Copy elements from the original array to the clone array
		for (int i = 0; i < size; i++) {
			cloneList.array[i] = array[i];
		}
		// Update the size of the clone list
		cloneList.size = size;

		// Return the clone
		return cloneList;
	}

	/*
	 * Returns an array containing all of the elements in this list in proper
	 * sequence (from first to last element)
	 */
	@Override
	public Object[] toArray() {

		// Creates a new array and copy the elements of this list into it
		Object[] new_Array = new Object[size];
		
		// Copy elements from the internal array to the new array
		for (int i = 0; i < size; i++) {
			new_Array[i] = array[i];
		}
		// Return the new array
		return new_Array;
	}

	// ---------------------------------------------------------------

	// --- Rör ej nedanstående kod -----------------------------------

	public MyArrayList(Collection<? extends E> c) {
		throw new UnsupportedOperationException();
	}

	private class InternalIterator implements Iterator {
		int current = 0;

		@Override
		public boolean hasNext() {
			return current < size();
		}

		@Override
		public Object next() {
			return get(current++);

		}

	}

	@Override
	public Iterator<E> iterator() {
		return new InternalIterator();
	}

	@Override
	public boolean addAll(int index, Collection<? extends E> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int lastIndexOf(Object o) {
		throw new UnsupportedOperationException();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		throw new UnsupportedOperationException();
	}

	@Override
	public ListIterator<E> listIterator(int index) {
		throw new UnsupportedOperationException();
	}

	@Override
	public ListIterator<E> listIterator() {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<E> subList(int fromIndex, int toIndex) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void forEach(Consumer<? super E> action) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Spliterator<E> spliterator() {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean removeIf(Predicate<? super E> filter) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void replaceAll(UnaryOperator<E> operator) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void sort(Comparator<? super E> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}

}
