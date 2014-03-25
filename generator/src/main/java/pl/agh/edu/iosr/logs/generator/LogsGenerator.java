package pl.agh.edu.iosr.logs.generator;

import org.apache.log4j.Logger;

/**
 * Indefinitely generates log4j logs.
 */
public class LogsGenerator {
	private Logger logger = Logger.getLogger("artificialLogger");

	private LogFactory logFactory;

	public LogsGenerator(LogFactory logFactory) {
		this.logFactory = logFactory;
	}

	public void startLogging() {
		Log log = logFactory.createLog();
		while (log != null) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
			log.doLog(logger);
		}
	}
}
