package pl.agh.edu.iosr.logs.reader.kafka;


public class ConsumerConsoleTest {
	
	private static String topic = "iosr";
	
	public static void shutdown(KafkaHelper kh, Thread thread)
			throws InterruptedException {
		if (kh != null)
			kh.shutdown();
		if (thread != null)
			thread.join();
	}

	public static void main(String[] args) throws InterruptedException {
		KafkaHelper kh = new KafkaHelper(topic);
		Thread thread = new Thread(new Consumer(kh.getStreams().get(0), 0, System.out));
		thread.start();

		try {
			Thread.sleep(10000);
		} catch (InterruptedException ie) {

		}
		shutdown(kh, thread);

	}
}
