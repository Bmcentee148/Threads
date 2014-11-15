public class TestOne implements Runnable {

	
	public TestOne() {
		//TestOne task = new TestOne();
		new Thread(this).start();
	}

	public void run() {
		System.out.println("test");
	}

	public static void main(String[] args) {
		new TestOne();
	}
}