public class ThreadsDemo {

	public static void main(String [] args) {

		TaskClass task = new TaskClass("Hello, world!"); //create task

		Thread thread = new Thread(task); //create thread and pass it the task

		thread.start(); //start the thread
	}
}