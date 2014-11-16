/* This is designed to show how one Thread can be 'joined' onto another */

public class JoinedThreads {

	public static void main(String[] args) {
		Thread thread1 = new Thread(new CustomTask());
		thread1.start();
	}
}//end main

class CustomTask implements Runnable {
	public void run() {
		Thread thread2 = new Thread(new Print<Character>('y',50));
		thread2.start();
		try{
			for(int i = 0; i<50; i++){
				System.out.print("x");
				if(i == 25){
					thread2.join();
				}
			}
		}catch(InterruptedException e) {
			e.printStackTrace();
		}	
	}//endrun
}//end CustomTask	

class Print<T> implements Runnable {
	T t;
	int n;
	public Print(T t, int n) {
		this.t = t;
		this.n = n;
	}
	public void run() {
		for(int i = 0; i < n; i++) {
			System.out.print(t);
		}
	}//end run
}//end PrinChar			
