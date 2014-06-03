package pl.agh.edu.iosr.logs.reader.kafka;

import java.io.CharArrayWriter;
import java.io.IOException;

import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.message.MessageAndMetadata;

import org.apache.log4j.Logger;

public class Consumer implements Runnable {
	private KafkaStream<byte[], byte[]> stream;
	private CharArrayWriter writer;

	private int offset = 1024;
	private int fileSize;

	private static Logger logger = Logger.getLogger(Consumer.class);
	
	public Consumer(KafkaStream<byte[], byte[]> a_stream, int a_threadNumber, CharArrayWriter a_writer) {
		fileSize = Integer.parseInt(System.getProperty("dfs.blocksize", "8192"));
		stream = a_stream;
		writer = a_writer;
	}

	public void run() {
		ConsumerIterator<byte[], byte[]> it = stream.iterator();
		int i = 0;
		
		while (it.hasNext()) {
			
			if (i >= 100){
				i = 0;
				logger.info("Progress: " + writer.size() + " bytes written");
			}
			i++;
			
			if (writer.size() + offset > fileSize) {
				Thread.currentThread().interrupt();
				break;
			} else {
				MessageAndMetadata<byte[], byte[]> message = it.next();
				String msg = new String(message.message());
				
				if (msg.equals("")) continue;
				
				try {
					writer.write(msg);
				} catch (IOException e) {
					logger.error("Error while writing: ", e);
				}
			}
			
		}
		return;
	}
}
