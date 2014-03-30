package pl.agh.edu.iosr.logs.generator;

import java.io.File;

/**
 * Program generating random logs with various levels.
 */
public class Generator {
	public static void main(String[] args) {
		LogFactory logFactory = LogFactoryProvider.createLogFactory(new File("shakespeare.in"));
		LogsGenerator logsGenerator = new LogsGenerator(10.0, logFactory);
		logsGenerator.startLogging();
	}
}
