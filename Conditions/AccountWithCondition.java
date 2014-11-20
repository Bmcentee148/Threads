/* This Account class will store the current balance, return the current balance,
	and deposit a certain amount. It is assumed that the balance and the deposit
	amount are in pennies. 

	This uses Conditions to make Threads cooperate with one another. */

import java.util.concurrent.locks.*;
import java.util.concurrent.*;
public class AccountWithCondition {

	private static Lock lock = new ReentrantLock(); //Create a Lock
	private static Condition newDeposit = lock.newCondition(); //Create Condition obj


	private int balance;

	public AccountWithCondition() {
		this.balance = 0;
	}

	public AccountWithCondition(int startBalance) {
		this.balance = startBalance;
	}

	public int getBalance() {
		return balance;
	}

	/*This method will deposit the given amount and then signal all waiting 
	Threads in withdraw() to try and continue execution. */
	public void deposit(int amount) {
		lock.lock(); //Acquire a lock
		try{
			balance += amount;
			
			System.out.println("Deposit " + amount + "\t\t\t\t\t" + getBalance());
			newDeposit.signalAll(); //alert awaiting Threads in withdraw()
		} finally {
			lock.unlock(); //Release the lock
		}
	}//end deposit

	/*This method will receive a signal from deposit to try and execute but will
	only do so if there are a sufficient amount of funds in the account. */
	public void withdraw(int amount) {
		lock.lock();
		try{
			while(balance < amount) {
				System.out.println("\t\tWait for deposit");
				newDeposit.await(); //signal Thread to wait
			}

			balance -= amount;
			System.out.println("\t\t\tWithdraw " + amount + "\t\t" + getBalance());
		} catch(InterruptedException ex) {
			ex.printStackTrace();
		} finally {
			lock.unlock();
		}

	}//end withdraw

}