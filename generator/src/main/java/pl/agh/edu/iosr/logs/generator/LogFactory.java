package pl.agh.edu.iosr.logs.generator;

import java.util.ArrayList;
import java.util.Random;

import org.apache.log4j.Level;

/**
 * Creates logs with random messages from the provided messages list.
 */
public class LogFactory {
	private Random random = new Random(System.currentTimeMillis() * System.identityHashCode(this));

	private ArrayList<String> messages;

	private Level level;

	public LogFactory(Level level, ArrayList<String> messages) {
		this.messages = messages;
		this.level = level;
	}

	public Log createLog() {
		return new Log(level, messages.get(random.nextInt(messages.size())));
	}

	public Level getLevel() {
		return level;
	}
}
