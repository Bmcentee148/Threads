
import java.util.concurrent.*; //needed for thread pool
import java.util.concurrent.locks.*; //needed for thread lock
import java.util.*;

public class ConsumerProducer {

	private static Buffer buffer = new Buffer();

	public static void main(String[] args) {
		//Create Thread Pool with two threads
		ExecutorService executor = Executors.newFixedThreadPool(2);
		executor.execute(new ProducerTask());
		executor.execute(new ConsumerTask());
		executor.shutdown();
	}

	//a task for adding an int to the buffer
	private static class ProducerTask implements Runnable {
		public void run() {
			try{
				int i = 1;
				while(true) {
					System.out.println("Producer writes " + i);
					buffer.write(i++); //write then increment
					//put the thread to sleep
					Thread.sleep((int)(Math.random() * 10000));
				}
			} catch(InterruptedException ex) {
				ex.printStackTrace();
			}
		}
	}

	//a task for reading and removing an int from the buffer
	private static class ConsumerTask implements Runnable {
		public void run() {
			try {
				while(true) {
					System.out.println("\t\t\tConsumer reads " + buffer.read());
					Thread.sleep((int)(Math.random() * 10000)); //put Thread to sleep
				}
			} catch(InterruptedException ex) {
				ex.printStackTrace();
			}
		}
	}

	//an inner class for buffer
	private static class Buffer {
		private static final int CAPACITY = 1; //buffer size
		private LinkedList<Integer> queue = new LinkedList<>();

		//Create new lock
		private static Lock lock = new ReentrantLock();

		//Create two conditions
		private static Condition notEmpty = lock.newCondition();
		private static Condition notFull = lock.newCondition();

		public void write(int val) {
			lock.lock(); //acquire lock
			try{
				while(queue.size() >= CAPACITY) {
					System.out.println("Wait for notFull condition");
					notFull.await(); 
				}

				queue.offer(val); //place val at tail end of queue
				notEmpty.signal(); //Signal notEmpty condition
			} catch(InterruptedException ex) 	{
				ex.printStackTrace();
			} finally {
				lock.unlock(); //release lock
			}
		}

		public int read() {
			int value = 0;
			lock.lock(); //Acquire the lock
			try {
				while(queue.isEmpty()) {
					System.out.println("\t\t\tWait for notEmpty condition");
					notEmpty.await();
				}

				value = queue.remove();
				notFull.signal(); //Signal notFull condition
			} catch(InterruptedException ex) {
				ex.printStackTrace();
			} finally {
				lock.unlock();
				return value;
			}
		}

	}//end Buffer
}