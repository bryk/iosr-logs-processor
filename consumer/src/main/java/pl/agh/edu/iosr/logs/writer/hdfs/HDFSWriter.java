package pl.agh.edu.iosr.logs.writer.hdfs;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.log4j.Logger;

import pl.agh.edu.iosr.logs.writer.IWriter;

public class HDFSWriter implements IWriter {

	private static Logger logger = Logger.getLogger(HDFSWriter.class);
	
	private FileSystem hdfs;

	public HDFSWriter(FileSystem hdfsFileSystem) {
		hdfs = hdfsFileSystem;
	}

	@Override
	public void writeToFile(String path, String something) {
		
		logger.info("Writing to file: " + path);
		
		FSDataOutputStream stream = null;
		
		try {
			stream = hdfs.create(new Path(path));
		} catch (IllegalArgumentException e1) {
			logger.error("Error while appending data.", e1);
		} catch (IOException e1) {
			logger.error("Error while appending data.", e1);
		}
		
		Writer writer = new OutputStreamWriter(stream);
		
		try {
			writer.write(something);
		} catch (IOException e) {
			logger.error("Error while writing to file.", e);
		} finally {
			try {
				writer.close();
			} catch (IOException e) {
				logger.error("Error while closing file.", e);
			}
		}
	}

	@Override
	public void appendToFile(String path, String something) {
		
		logger.info("Appending to file: " + path);
		
		FSDataOutputStream stream = null;
		
		try {
			stream = hdfs.append(new Path(path));
		} catch (IllegalArgumentException e1) {
			logger.error("Error while appending data.", e1);
		} catch (IOException e1) {
			logger.error("Error while appending data.", e1);
		}
		
		Writer writer = new OutputStreamWriter(stream);
		
		try {
			writer.write(something);
		} catch (IOException e) {
			logger.error("Error while writing to file.", e);
		} finally {
			try {
				writer.close();
			} catch (IOException e) {
				logger.error("Error while closing file.", e);
			}
		}
	}

}
