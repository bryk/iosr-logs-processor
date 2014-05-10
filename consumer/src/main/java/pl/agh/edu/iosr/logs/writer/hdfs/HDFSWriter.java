package pl.agh.edu.iosr.logs.writer.hdfs;

import java.io.IOException;

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
	public void writeToFile(String path, String something) throws IOException {
		FSDataOutputStream stream = hdfs.create(new Path(path));
		stream.writeChars(something);
		stream.flush();
		stream.close();
	}

	@Override
	public void appendToFile(String path, String something) throws IOException {
		FSDataOutputStream stream = hdfs.append(new Path(path));
		stream.writeChars(something);
		stream.flush();
		stream.close();
	}

}
