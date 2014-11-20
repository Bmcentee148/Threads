/* This Account class will store the current balance, return the current balance,
	and deposit a certain amount. It is assumed that the balance and the deposit
	amount are in pennies. 

	We explicitly place a lock on the class when calling the deposit method. 
	It is good practice to always immediately follow a call to lock() with a
	try-catch block and release the lock in the finally clause, to ensure it 
	is actually released. */

import java.util.concurrent.locks.*;
import java.util.concurrent.*;
public class AccountWithSync {

	private static Lock lock = new ReentrantLock(); //Create a Lock
	private int balance;

	public AccountWithSync() {
		this.balance = 0;
	}

	public AccountWithSync(int startBalance) {
		this.balance = startBalance;
	}

	public int getBalance() {
		return balance;
	}

	//This is the "critical region" of the program. i.e. we should not allow
	//	more than one Thread to access this part of the program at a time
	public void deposit(int amount) {
		lock.lock(); //Acquire a lock
		try{
			int newBalance = balance + amount;
			Thread.sleep(5); //added to make magnify corruption & make visible
			this.balance = newBalance;
		} catch(InterruptedException ex) {
			ex.printStackTrace();
		} finally {
			lock.unlock(); //Release the lock
		}
	}

}