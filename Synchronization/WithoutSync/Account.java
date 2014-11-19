/* This Account class will store the current balance, return the current balance,
	and deposit a certain amount. It is assumed that the balance and the deposit
	amount are in pennies. 

	This is an example of a race condition between Threads. This is NOT THREAD
	SAFE. Corruption occurs due to multiple Threads accessing the same resource
	in a way that causes conflict. 

	This class is NOT THREAD SAFE	*/

public class Account {

	private int balance;

	public Account() {
		this.balance = 0;
	}

	public Account(int startBalance) {
		this.balance = startBalance;
	}

	public int getBalance() {
		return balance;
	}

	//This is the "critical region" of the program. i.e. we should not allow
	//	more than one Thread to access this part of the program at a time
	public void deposit(int amount) {
		int newBalance = balance + amount;
		try{
			Thread.sleep(5); //added to make magnify corruption & make visible
		}catch(InterruptedException ex) {
			ex.printStackTrace();
		}
		this.balance = newBalance;
	}
}