package pl.agh.edu.iosr.logs.reader.kafka;

import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.message.MessageAndMetadata;

public class Consumer implements Runnable {
	private KafkaStream<byte[], byte[]> stream;
	private CharArrayWriter writer;

	private int offset = 1024;
	private int fileSize;

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
				System.err.println(writer.size());
			}
			i++;
			if (writer.size() + offset > fileSize) {
				System.err.println("RETURN");
				Thread.currentThread().interrupt();
				break;
			} else {
				MessageAndMetadata<byte[], byte[]> message = it.next();
				String msg = "";
				try {
					msg = new String(message.message(), "ASCII") + "\n";
				} catch (UnsupportedEncodingException e1) {
					System.out.println(e1);
				}
//				System.out.println(new String(message.message()));
				try {
					writer.write(msg);
				} catch (IOException e) {
					System.err.println("Error while writing: " + e.toString());
				}
			}
			
		}
		return;
	}
}
