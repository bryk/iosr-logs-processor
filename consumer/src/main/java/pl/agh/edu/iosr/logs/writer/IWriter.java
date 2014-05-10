package pl.agh.edu.iosr.logs.writer;

import java.io.IOException;

public interface IWriter {
	void writeToFile(String path, String something) throws IOException;
	void appendToFile(String path, String something) throws IOException;
}