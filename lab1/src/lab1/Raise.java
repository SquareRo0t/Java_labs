package lab1;

public class Raise {

	// Task 8 and 9

//	recursive method to raise a number to the power of k using multiplication
	public static double recRaiseOne(double x, int k) {

		if (k == 0) {
			return 1.0; // base case x^0 is 1

		} else {
			countOne++; // count the recursive calls
			return x * recRaiseOne(x, k - 1); // recursive call: x*x^(k-1)
		}
	}

//	recursive method to raise a number to the power of k by dividing k by 2
	public static double recRaiseHalf(double x, int k) {
		if (k == 0) {
			return 1.0; // base case x^0 is 1
		}

		// variable created after the important notes
		double variable = recRaiseHalf(x, k / 2);

		if (k % 2 == 0) {
			countHalf++; // count the recursive calls
			return variable * variable;

		} else {
			countHalf++; // count the recursive calls
			return x * variable * variable;
		}

	}

	// variables to count the number of recursive calls for each method
	private static int countOne = 0;
	private static int countHalf = 0;

	public static void main(String[] args) {

		// test for the recursive methods for different values of k
		for (int k = 1; k <= 15; k++) {

			countOne = 0; // reset the count for recRaiseOne
			countHalf = 0; // reset the count for recRaseHalf

			System.out.println("");
			System.out.println(
					"recRaiseOne when k=" + k + ": " + recRaiseOne(1.5, k) + " Recursive calls: " + countOne);
			System.out.println(
					"recRaiseHalf when k=" + k + ": " + recRaiseHalf(1.5, k) + " Recursive calls: " + countHalf);

//			some tests
//			System.out.println(recRaiseOne(2,k));
//			System.out.println(recRaiseHalf(2,k));

		}
	}
}
