package pl.agh.edu.iosr.logs.generator;

import java.io.File;
import java.util.ArrayList;

import org.apache.log4j.Level;

public class LogFactoryProvider {
	public static LogFactory createLogFactory(Level level, File messagesFile) {
		ArrayList<String> messages = new ArrayList<>();
		// TODO(bryk): Get messages from file.
		messages.add("abc");
		messages.add("def");
		messages.add("ghi");
		return new LogFactory(level, messages);
	}

	private LogFactoryProvider() {
	}
}
