/* This will used synchronized Threads in order to perform many deposits on 
	a single Account object. Synchronizing will enable us to avoid corruption by 
	allowing access to the critical region, the deposit method, by a single 
	Thread at a time. Each Thread must first lock onto the object, use the method,
	and then release the object before another Thread can execute on it. */

import java.util.concurrent.*; //needed for Executors class

public class AccountWithConditionDemo {
	
	private static AccountWithCondition account;
	
	public static void main(String[] args) {
		account = new AccountWithCondition();
		ExecutorService executor = Executors.newFixedThreadPool(2);

		System.out.println("Thread 1\t\tThread 2\t\tBalance");
		
		executor.execute(new AddPennyTask());
		executor.execute(new WithdrawPennyTask());
		

		executor.shutdown();

		while(!executor.isTerminated()) {
			//wait until all tasks are terminated
		}

		System.out.println("What is the balance? " + account.getBalance());
	}//end main

	//private static inner class for the task of adding a penny
	private static class AddPennyTask implements Runnable {

		public void run() {
			try{
				while(true) {
					account.deposit((int)(Math.random() * 10) + 1);
					Thread.sleep(1000); //purposely delay to allow withdraw exec
				}
			} catch(InterruptedException ex) {
				ex.printStackTrace();
			}	
		}
	}//end AddPennyTask

	//private static inner class for withdraw task
	private static class WithdrawPennyTask implements Runnable {

		public void run() {
			while(true)
				account.withdraw((int)(Math.random() * 10) + 1);
		}
	}

}//end AccountWithoutSyncDemo

