/* This will used synchronized Threads in order to perform many deposits on 
	a single Account object. Synchronizing will enable us to avoid corruption by 
	allowing access to the critical region, the deposit method, by a single 
	Thread at a time. Each Thread must first lock onto the object, use the method,
	and then release the object before another Thread can execute on it. */

import java.util.concurrent.*; //needed for Executors class

public class AccountWithSyncDemo {
	
	private static AccountWithSync account;
	
	public static void main(String[] args) {
		account = new AccountWithSync();
		ExecutorService executor = Executors.newCachedThreadPool();

		for(int i = 0; i< 100; i++) {
			executor.execute(new AddPennyTask());
		}

		executor.shutdown();

		while(!executor.isTerminated()) {
			//wait until all tasks are terminated
		}

		System.out.println("What is the balance? " + account.getBalance());
	}//end main

	//private static inner class for the task of adding a penny
	private static class AddPennyTask implements Runnable {

		public void run() {
			account.deposit(1);
		}
	}//end AddPennyTask

}//end AccountWithoutSyncDemo

