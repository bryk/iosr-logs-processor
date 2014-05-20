package pl.agh.edu.iosr.logs.reader.kafka;

import java.io.CharArrayWriter;


public class ConsoleConsumer {
	
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
		CharArrayWriter writer = new CharArrayWriter();
		Thread thread = new Thread(new Consumer(kh.getStreams().get(0), 0, writer));
		thread.start();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException ie) {

		}
		System.out.println(writer.toString());
		shutdown(kh, thread);

	}
}
