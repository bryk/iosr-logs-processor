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
		String fs = System.getProperty("fs.defaultFS", "hdfs://localhost:8020");
		String fileSize = System.getProperty("dfs.blocksize", "15000");
		System.setProperty("dfs.blocksize", fileSize);
		config = conf;
		config.set("fs.hdfs.impl", org.apache.hadoop.hdfs.DistributedFileSystem.class.getName());
		config.set("fs.file.impl", org.apache.hadoop.fs.LocalFileSystem.class.getName());
		hdfs = FileSystem.get( new URI(fs), config);
	}

	public FileSystem getHdfs() {
		return hdfs;
	}
}
