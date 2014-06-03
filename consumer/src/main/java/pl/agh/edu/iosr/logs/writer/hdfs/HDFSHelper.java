package pl.agh.edu.iosr.logs.writer.hdfs;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.log4j.Logger;

public class HDFSHelper {
	
	private Configuration config;
	private FileSystem hdfs;
	
	private static Logger logger = Logger.getLogger(HDFSHelper.class);
	
	public HDFSHelper(Configuration conf) throws IOException, URISyntaxException {
		
		logger.info("Creating filesystem");
		logger.debug("Setting properties");
		
		String fs = System.getProperty("fs.defaultFS", "hdfs://localhost:8020");
		String fileSize = System.getProperty("dfs.blocksize", "15000");
		System.setProperty("dfs.blocksize", fileSize);
		config = conf;
		config.set("fs.hdfs.impl", org.apache.hadoop.hdfs.DistributedFileSystem.class.getName());
		config.set("fs.file.impl", org.apache.hadoop.fs.LocalFileSystem.class.getName());
		
		logger.debug("Getting filesystem");
		
		hdfs = FileSystem.get( new URI(fs), config);
		
		logger.info("Filesystem created");
	}

	public FileSystem getHdfs() {
		return hdfs;
	}
}
