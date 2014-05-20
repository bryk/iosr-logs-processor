package pl.agh.edu.iosr.logs.writer.hdfs;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import pl.agh.edu.iosr.logs.writer.IWriter;

public class HDFSWriter implements IWriter {

	private FileSystem hdfs;

	public HDFSWriter(FileSystem hdfsFileSystem) {
		hdfs = hdfsFileSystem;
	}

	@Override
	public void writeToFile(String path, String something) {
		FSDataOutputStream stream = null;
		try {
			stream = hdfs.create(new Path(path));
		} catch (IllegalArgumentException e1) {
			System.err.println(e1);
		} catch (IOException e1) {
			System.err.println(e1);
		}
		Writer writer = new OutputStreamWriter(stream);
		try {
			writer.write(something);
		} catch (IOException e) {
			System.err.println("Write error: " + e);
		} finally {
			try {
				writer.close();
			} catch (IOException e) {
				System.err.println("Close error: " + e);
			}
		}
	}

	@Override
	public void appendToFile(String path, String something) {
		FSDataOutputStream stream = null;
		try {
			stream = hdfs.append(new Path(path));
		} catch (IllegalArgumentException e1) {
			System.err.println(e1);
		} catch (IOException e1) {
			System.err.println(e1);
		}
		Writer writer = new OutputStreamWriter(stream);
		try {
			writer.write(something);
		} catch (IOException e) {
			System.err.println("Write error: " + e);
		} finally {
			try {
				writer.close();
			} catch (IOException e) {
				System.err.println("Close error: " + e);
			}
		}
	}

}
