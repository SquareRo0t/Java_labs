package lab1;

import java.util.Scanner;

public class LifeLength {

	// Main method for user interaction and task selection
	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in); // creates a scanner objects to read inputs from console

		System.out.println("Enter a value for n:");
		int n = scanner.nextInt();

		switch (n) {

		case 1:
			rTask1();
			break; 

		case 2:
			rTask2();
			break;

		case 3:
			rTask3();
			break;

		case 4:
			rTask4();
			break;

		case 6:
			rTask6();
			break;

		default:
			System.out.println("Invalid value for n. Please choose a n value between 1, 2, 3, 4 and 6");
		}
		// ensures scanner is closed
		scanner.close();
	}

//---------------------------Task 1-------------------------------------//

	// function for task 1
	public static int f1(int a0) {

		// if the inputs is 1, return 1
		if (a0 == 1) {
			return a0;
		}
		// if the input is even, return a0 divided by 2
		if (a0 % 2 == 0) {
			return a0 / 2;

		} else {
			// if the input is odd, return (3 * a0 + 1)
			return (3 * a0 + 1);
		}
	}

	// runTask1: iteratively run f1 with user input
	public static void rTask1() {

		Scanner scanner = new Scanner(System.in);

		// loop to repeatedly take user inputs and calculate result
		while (true) {

			System.out.println("Enter a value for a0:");
			int a0 = scanner.nextInt();

			// if user enters 0, terminate the progress
			if (a0 == 0) {
				System.out.println("Program terminated");
				break;
			}
			// calculate the result using f1
			int result = f1(a0);
			System.out.println("Result for task 1: " + result);
		}
		scanner.close();
	}
//----------------------------------------------------------------------//	

//---------------------------Task 2-------------------------------------//
	// Apply f1 twice
	public static int f2(int a0) {
		return f1(f1(a0));
	}

	// Apply f2 twice
	public static int f4(int a0) {
		return f2(f2(a0));
	}

	// Apply f4 twice
	public static int f8(int a0) {
		return f4(f4(a0));
	}

	// Apply f8 twice
	public static int f16(int a0) {
		return f8(f8(a0));
	}

	// Apply f16 twice
	public static int f32(int a0) {
		return f16(f16(a0));
	}

	// runTask 2
	public static void rTask2() {

		System.out.println("Task 2");
		System.out.println("Enter an interger greater than 0");
		Scanner scanner = new Scanner(System.in);

		// read user input
		int a0 = scanner.nextInt();

		// less than or equal to 0 print error
		if (a0 <= 0) {
			System.out.println("Error: Please enter a valid interger greater than 0");
		} else {

			System.out.println("f1=" + f1(a0) + " f2=" + f2(a0) + " f4=" + f4(a0) + " f8=" + f8(a0) + " f16=" + f16(a0)
					+ " f32=" + f32(a0));
		}
		scanner.close();
	}
//----------------------------------------------------------------------//

//---------------------------Task 3-------------------------------------//
	public static int iterateF(int a0, int n) {
		for (int i = 0; i < n; i++) {
			a0 = f1(a0);
		}
		return a0;
	}

	// runTask 3
	public static void rTask3() {
		Scanner scanner = new Scanner(System.in);

		int n = 0;
		int a0 = 0;

		while (true) {

			// Get user input for a0
			System.out.println("Enter a start value (a0)");
			// reads a int value from user
			a0 = scanner.nextInt();

			// If 0 is entered, exit the loop
			if (a0 == 0) {
				System.out.println("Existing the program");
				break;
			}
			System.out.println("Enter the number of steps to iterate");
			n = scanner.nextInt();

			if (n < 0) {
				System.out.println("Error: Please enter a non-negative interger for n");
				continue;
			}
			System.out.println("After " + n + " steps: " + iterateF(a0, n));
		}
		scanner.close();
	}
//----------------------------------------------------------------------//

//---------------------------Task 4-------------------------------------//
	public static int iterLifeLength(int a0) {

		// initialize a count to keep track of the number of iterations
		int count = 0;

		// iterate until a0 becomes 1
		while (a0 != 1) {
			// apply f1 function to a0 and increment the count
			a0 = f1(a0);
			count += 1;
		}
		return count;
	}

	// function to represent life length
	public static String intsToString(int X, int Y) {
		return ("The life lenght of " + X + " is " + Y + ".");
	}

	// runTask 4
	public static void rTask4() {
		for (int i = 1; i <= 15; i++) {
			System.out.println(intsToString(i, iterLifeLength(i)));
		}
	}
//----------------------------------------------------------------------//

//---------------------------Task 6-------------------------------------//
	// recursive function to calculate life length
	public static int recLifeLength(int a0) {
		// if a0 is 1, return 0
		if (a0 == 1) {
			return 0;
		} else {
			// call recLifeLength with the result of f1(a0) and add 1
			return recLifeLength(f1(a0)) + 1;
		}
	}

	// runTask 6
	public static void rTask6() {
		for (int i = 1; i <= 15; i++) {
			System.out.println("iterLifeLength " + iterLifeLength(i) + ", " + "recLifeLength " + recLifeLength(i));
		}
	}
//----------------------------------------------------------------------//
}