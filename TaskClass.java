
public class TaskClass implements Runnable {
	private String word;

	public TaskClass(String word) {
		this.word = word;
	}

	public void run() {
		System.out.println(word);
	}


}