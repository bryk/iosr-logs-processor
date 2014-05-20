package pl.agh.edu.iosr.logs.reader.kafka;

import java.io.CharArrayWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Date;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;

import pl.agh.edu.iosr.logs.writer.IWriter;
import pl.agh.edu.iosr.logs.writer.hdfs.HDFSHelper;
import pl.agh.edu.iosr.logs.writer.hdfs.HDFSWriter;

public class HdfsConsumer {
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
		
		HDFSHelper client = new HDFSHelper(new Configuration());
		FileSystem hdfsFs = client.getHdfs();
		IWriter HDFSWriter = new HDFSWriter(hdfsFs);
		
		
		int fileSize = Integer.parseInt(System.getProperty("dfs.blocksize"));
		CharArrayWriter writer = new CharArrayWriter(fileSize);
		
		while (true){
			KafkaHelper kh = new KafkaHelper(topic);
			writer.reset();
			Thread thread = new Thread(new Consumer(kh.getStreams().get(0), 0, writer));
			thread.start();
			thread.join();
			System.err.println("JOIN");
			
			Date now = new Date();
			String path = hdfsFs.getHomeDirectory() + "/kafka/" + now.getTime();
			
			HDFSWriter.writeToFile(path, writer.toString());
			shutdown(kh, thread);
			
		}
	}
}
