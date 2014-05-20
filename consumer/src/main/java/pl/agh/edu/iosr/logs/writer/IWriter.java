package pl.agh.edu.iosr.logs.writer;

public interface IWriter {
	void writeToFile(String path, String something);
	void appendToFile(String path, String something);
}
