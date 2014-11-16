/*	This was designed to show how to conditionally put a Thread to sleep,
	allowing another to run, when we have two Threads running concurrently. */

public class ThreadOps {

	public static void main(String[] args) {
		Thread thread1 = new Thread(new CustomTask());
		Thread thread2 = new Thread( () -> { for(int i = 0; i<50; i++)
												System.out.println("y"); });

		thread1.start();
		thread2.start();

	}
}

class CustomTask implements Runnable {
	public void run() {
		//Wrap the loop in a try-catch to guarantee we end the loop on interrupt
		try{

			for(int i = 0; i<50; i++){
				System.out.println("x");
				if(i>25) Thread.sleep(1); //Put thread to sleep for 1 milsec	
			}

		}catch(InterruptedException e) {
			System.err.println(e); 
		}//end try-catch
	}//endrun
}				
