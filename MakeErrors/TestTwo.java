public class TestTwo implements Runnable{
	
	public static void main(String[] args) {
		new TestTwo();
	}

	public TestTwo() {
		Thread t = new Thread(this);
		Thread t2 = new Thread(this);
		t.start();
		t2.start();
	}

	public void run() {
		System.out.println("test");
	}
}