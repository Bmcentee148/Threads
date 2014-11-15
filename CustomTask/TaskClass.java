/*
IDEA:
Any class that intends its instances to be run by a thread MUST implements the
Runnable interface. This is a functional interface with the abstract method
run. It takes no arguments.
*/
public class TaskClass implements Runnable {
	private String word;

	public TaskClass(String word) {
		this.word = word;
	}

	public void run() {
		System.out.println(word);
	}


}