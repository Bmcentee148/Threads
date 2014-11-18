/** This program demonstrates the use of a Thread pool to execute multiple
	Threads. It is inefficient to create each Thread individually if there are 
	many Threads to execute. In this case, we use the Executor and ExecutorService
	Interface to create a Thread pool. */

import java.util.concurrent.*; //needed for Executors Class

public class ExecutorDemo {
	
	public static void main(String [] args) {

		//create ExecutorService using static method from Executors class
		ExecutorService executor = Executors.newFixedThreadPool(3);

		//execute three threads in the pool
		executor.execute(new Print<Integer>(3,100));
		executor.execute(new Print<Character>('x',100));
		executor.execute(new Print<String>("hi",100));
		
		//terminate thread pool
		executor.shutdown();

	}

}

class Print<T> implements Runnable {
	T t;
	int n;
	public Print(T t, int n) {
		this.t = t;
		this.n = n;
	}
	public void run() {
		for(int i = 0; i < n; i++) {
			System.out.println(t);
		}
	}//end run
}//end PrinChar