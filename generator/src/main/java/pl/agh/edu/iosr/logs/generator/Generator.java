package pl.agh.edu.iosr.logs.generator;

import java.io.InputStream;

import org.apache.log4j.Level;

/**
 * Program generating random logs with various levels.
 */
public class Generator {
	public static void main(String[] args) {
		Generator generator = new Generator();
		generator.start();
	}

	private void start() {
		runLogger(Level.INFO, 10.0);
		runLogger(Level.WARN, 3.2);
		runLogger(Level.DEBUG, 2.0);
		runLogger(Level.ERROR, 0.134);
	}

	private void runLogger(Level level, double logsPerSecond) {
		InputStream messagesStream = this.getClass().getResourceAsStream("/romeoandjuliet.txt");
		LogFactory logFactory = LogFactoryProvider.createLogFactory(level, messagesStream);
		final LogsGenerator logsGenerator = new LogsGenerator(logsPerSecond, logFactory);
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				logsGenerator.startLogging();
			}
		});

		thread.start();
	}
}
