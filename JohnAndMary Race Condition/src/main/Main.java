/*
 *  * Bill Nicholson
 * nicholdw@ucmail.uc.edu
 * 
 * The starting balance is $42.
 * We will make two deposits to the same account from two threads.
 * John deposits $100
 * Mary Deposits $500
 * The final balance should be $642
 * 
 * If the code is not synchronized properly, we will get other balances. oopsy.
 */
package main;

public class Main {

	/**
	 * @param args
	 *            the command line arguments
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		System.out.println("Making two deposits...");
		Demo();
		System.out.println("Main exiting");
	}

	/**
	 * Make two deposits to the same account in separate threads. We start with
	 * a balance of $42: John deposits $100, Mary deposits $500.
	 * @throws Exception 
	 */
	private static void Demo() throws Exception {
		// int i;
		// We can access static class members with the class name, we don't need an object name
		MakeDeposit.balance = 42;

		/*
		 * We need two instances of the class, each running in a separate
		 * thread
		 */
		MakeDeposit johnThread = new MakeDeposit(100);
		MakeDeposit maryThread = new MakeDeposit(500);

		johnThread.start();
		maryThread.start();

		// Wait for both threads to end... it's ok to eat the exception,
		// probably
		try {
			johnThread.join();
		} catch (InterruptedException e) {
		}
		try {
			maryThread.join();
		} catch (InterruptedException e) {
		}

		// Print the final balance
		System.out.println("The final balance is " + MakeDeposit.balance);
	}
}