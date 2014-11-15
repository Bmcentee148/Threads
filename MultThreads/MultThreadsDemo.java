public class MultThreadsDemo {

	public static void main(String [] args) {

		Thread thread1 = new Thread(new Print<Integer>(3,100));
		Thread thread2 = new Thread(new Print<Character>('x',100));
		Thread thread3 = new Thread(new Print<String>("hi",100));
		thread1.start();
		thread2.start();
		thread3.start();
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

 	