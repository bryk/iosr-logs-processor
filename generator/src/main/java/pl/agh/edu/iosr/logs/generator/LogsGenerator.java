package pl.agh.edu.iosr.logs.generator;

import org.apache.log4j.Logger;

/**
 * Indefinitely generates log4j logs.
 */
public class LogsGenerator {
	private Logger logger = Logger.getLogger("artificialLogger");

	public LogsGenerator() {
	}

	public void startLogging() {
		while (true) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
			logger.info("Sample log");
		}
	}
}
