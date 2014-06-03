package pl.agh.edu.iosr.logs.reader.kafka;

import java.io.CharArrayWriter;

import org.apache.log4j.Logger;


public class ConsoleConsumer {
	
	private static String topic = "iosr";
	
	private static Logger logger = Logger.getLogger(ConsoleConsumer.class);
	
	private final static long time = 10000;
	
	public static void shutdown(KafkaHelper kh, Thread thread)
			throws InterruptedException {
		
		logger.info("Shutting down console consumer");
		
		if (kh != null)
			kh.shutdown();
		if (thread != null)
			thread.join();
	}

	public static void main(String[] args) throws InterruptedException {
		
		logger.info("Console consumer init");
		
		KafkaHelper kh = new KafkaHelper(topic);
		CharArrayWriter writer = new CharArrayWriter();
		
		logger.info("Console consumer initialized properly");
		
		
		Thread thread = new Thread(new Consumer(kh.getStreams().get(0), 0, writer));
		logger.info("Starting thread for " + time + " miliseconds");
		thread.start();
		try {
			Thread.sleep(time);
		} catch (InterruptedException ie) {

		}
		System.out.println(writer.toString());
		shutdown(kh, thread);

	}
}
