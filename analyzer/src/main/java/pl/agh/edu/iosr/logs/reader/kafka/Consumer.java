package pl.agh.edu.iosr.logs.reader.kafka;

import java.io.IOException;
import java.io.OutputStream;

import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.message.MessageAndMetadata;

public class Consumer implements Runnable {
	private KafkaStream<byte[], byte[]> stream;
	private int threadNumber;
	private OutputStream os;

	public Consumer(KafkaStream<byte[], byte[]> a_stream, int a_threadNumber,
			OutputStream a_os) {
		threadNumber = a_threadNumber;
		stream = a_stream;
		os = a_os;
	}

	public void run() {
		ConsumerIterator<byte[], byte[]> it = stream.iterator();
		while (it.hasNext()) {
			MessageAndMetadata<byte[], byte[]> message = it.next();
			String msg = "Thread " + threadNumber + ": "
					+ new String(message.message()) + "\n";
			try {
				os.write(msg.getBytes());
			} catch (IOException e) {
				System.err.println(e.toString());
				e.printStackTrace();
			}
		}
	}
}
