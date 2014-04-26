package pl.agh.edu.iosr.logs.writer;

import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;

import pl.agh.edu.iosr.logs.writer.hdfs.HDFSHelper;
import pl.agh.edu.iosr.logs.writer.hdfs.HDFSWriter;

public class WriterTest {
	public static void main(String[] args) throws IOException, URISyntaxException {
		HDFSHelper client = new HDFSHelper(new Configuration());
		FileSystem hdfsFs = client.getHdfs();
		IWriter writer = new HDFSWriter(hdfsFs);
		String path = hdfsFs.getHomeDirectory() + "/test.file2";
		writer.writeToFile(path, "Something new!\n");
		//does not work in < 2 nodes cluster
		//writer.appendToFile(path, "something else appended\n");
	}
}
