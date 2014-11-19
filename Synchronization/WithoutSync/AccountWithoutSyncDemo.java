import java.util.concurrent.*; //needed for Executors class

public class AccountWithoutSyncDemo {
	
	private static Account account;
	
	public static void main(String[] args) {
		account = new Account();
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

