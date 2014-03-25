package pl.agh.edu.iosr.logs.generator;

/**
 * Program generating random logs with various levels.
 */
public class Generator {
	public static void main(String[] args) {
		LogsGenerator logsGenerator = new LogsGenerator(new LogFactory());
		logsGenerator.startLogging();
	}
}
