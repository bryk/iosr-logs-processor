package pl.agh.edu.iosr.logs.generator;

/**
 * Program generating random logs with various levels.
 */
public class Generator {
	public static void main(String[] args) {
		System.out.println("Hello World!");

		LogsGenerator logsGenerator = new LogsGenerator();
		logsGenerator.startLogging();
	}
}
