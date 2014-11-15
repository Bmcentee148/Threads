
public class ThreadOps {

	public static void main(String[] args) {
		Thread thread1 = new Thread(new CustomTask());
		Thread thread2 = new Thread( () -> { for(int i = 0; i<50; i++)
												System.out.println(3); });

			thread1.start();
			thread2.start();

	}
}

class CustomTask implements Runnable {
	public void run() {
		for(int i = 0; i<50; i++){
			System.out.println(2);
			try{
				if(i>25) Thread.sleep(1); //Stop or yield first thread
			}catch(InterruptedException e) {
				System.err.println(e); 
			}
		}
	}
}