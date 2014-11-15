/*
This will create a Runnable task object and implement its run() method all with
a single lambda expression. This means we do not write our custom task class any
more. We create the Runnable object at the same time we create the Thread it 
will run on.
*/

public class LambdaTaskDemo {

	public static void main(String[] args) {

		//create thread and task
		Thread thread = new Thread( () -> System.out.println("Hello, World!"));
		thread.run(); //run thread
	}
}