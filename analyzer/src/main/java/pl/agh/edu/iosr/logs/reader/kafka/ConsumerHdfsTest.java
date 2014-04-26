package pl.agh.edu.iosr.logs.reader.kafka;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URISyntaxException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;

import pl.agh.edu.iosr.logs.writer.IWriter;
import pl.agh.edu.iosr.logs.writer.hdfs.HDFSHelper;
import pl.agh.edu.iosr.logs.writer.hdfs.HDFSWriter;

public class ConsumerHdfsTest {
	private static String topic = "iosr";

	public static void shutdown(KafkaHelper kh, Thread thread)
			throws InterruptedException {
		if (kh != null)
			kh.shutdown();
		if (thread != null)
			thread.join();
	}

	public static void main(String[] args) throws InterruptedException,
			IOException, URISyntaxException {
		KafkaHelper kh = new KafkaHelper(topic);
		HDFSHelper client = new HDFSHelper(new Configuration());
		FileSystem hdfsFs = client.getHdfs();
		IWriter writer = new HDFSWriter(hdfsFs);
		String path = hdfsFs.getHomeDirectory() + "/kafka_test";
		OutputStream os = new ByteArrayOutputStream();
		Thread thread = new Thread(new Consumer(kh.getStreams().get(0), 0, os));
		thread.start();

		try {
			Thread.sleep(10000);
		} catch (InterruptedException ie) {

		}
		writer.writeToFile(path, os.toString());
		shutdown(kh, thread);

	}
}
