package pl.agh.edu.iosr.logs.writer.hdfs;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;

public class HDFSHelper {
	
	private Configuration config;
	private FileSystem hdfs;
	
	public HDFSHelper(Configuration conf) throws IOException, URISyntaxException {
		config = conf;
		hdfs = FileSystem.get( new URI("hdfs://localhost:8020"), config);
	}

	public FileSystem getHdfs() {
		return hdfs;
	}
}
