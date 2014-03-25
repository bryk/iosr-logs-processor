package pl.agh.edu.iosr.logs.generator;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 * Represents a log4j's log instance.
 */
public class Log {
	private Level level;

	private String message;

	public Log(Level level, String message) {
		this.level = level;
		this.message = message;
	}

	/**
	 * Logs this log using the provided logger.
	 */
	public void doLog(Logger logger) {
		logger.log(level, message);
	}
}
