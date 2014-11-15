public class MultThreadsDemo {

	public static void main(String [] args) {

		Thread thread1 = new Thread(new Print<Integer>(3,10));
		Thread thread2 = new Thread(new Print<Character>('x',3));
		Thread thread3 = new Thread(new Print<String>("hi",3));
		thread1.run();
		thread2.run();
		thread3.run();
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

