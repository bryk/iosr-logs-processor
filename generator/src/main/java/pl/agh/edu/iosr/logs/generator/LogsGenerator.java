package pl.agh.edu.iosr.logs.generator;

import org.apache.log4j.Logger;

/**
 * Indefinitely generates log4j logs.
 */
public class LogsGenerator {
	private Logger logger;

	private LogFactory logFactory;

	private double logsPerSecond;

	public LogsGenerator(double logsPerSecond, LogFactory logFactory) {
		this.logFactory = logFactory;
		this.logsPerSecond = logsPerSecond;
		this.logger = Logger.getLogger(this.getClass());
	}

	public void startLogging() {
		Log log = logFactory.createLog();
		while (log != null) {
			int timeToSleep = (int) ((1.0 / logsPerSecond) * 1000.0);
			try {
				Thread.sleep(timeToSleep);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
			log.doLog(logger);
			log = logFactory.createLog();
		}
	}
}
