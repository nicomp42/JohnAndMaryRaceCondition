/*
 * Bill Nicholson
 * nicholdw@ucmail.uc.edu
 */
package main;

import java.util.Random;

/**
 * Simulate someone making a deposit at the ATM
 * A simple thread class that can be launched from another class.
 * <BR>This class inherits from the Java thread class.
 * <BR>The run() method overrides a method with the same signature in the base class.
 * <BR>
 * @author Nicholson.Bill
 */
public class MakeDeposit extends Thread {

	// balance is shared across all instances of the class. There's only one copy of the variable
	static float balance;
	private float depositAmount;
/**
 *  This method is called polymorphically from the base class.
 */
    public void run() {
    	doIt();	// Make the deposit
    }
    /**
     * Constructor
     */
    public MakeDeposit(float depositAmount) {
    	this.depositAmount = depositAmount;
    }
    /**
     * It's synchronized so it's atomic and deterministic. woo hoo. 
     */
    private void doIt() {
    	// TODO: Take out the "synchronized" statement to break this code.
    	synchronized (MakeDeposit.class){	// Only one instance of this thread can be here at a time.    	
    		// Only one instance of this thread can be here at a time.
	    	float temp = balance;
	    	temp = temp + depositAmount;
	    	// Introduce a little real-word randomness...
	    	try {Thread.sleep(new Random().nextInt(10));} catch (Exception ex){}	// Definitely OK to eat
	    	balance = temp;
    	}
    }
    /**
     * Get the balance on the account
     * @return The balance
     */
    public float getBalance(){return balance;}

}
