package pl.agh.edu.iosr.logs.generator;

import org.apache.log4j.Level;

public class LogFactory {
	public Log createLog() {
		return new Log(Level.INFO, "A log");
	}
}
