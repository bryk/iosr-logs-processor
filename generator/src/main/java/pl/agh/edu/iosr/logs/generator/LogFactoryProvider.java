package pl.agh.edu.iosr.logs.generator;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

import org.apache.log4j.Level;

public class LogFactoryProvider {
	public static LogFactory createLogFactory(Level level, InputStream messagesStream) {
		ArrayList<String> messages = new ArrayList<>();
		try (Scanner scanner = new Scanner(messagesStream)) {
			while (scanner.hasNextLine()) {
				String msg = scanner.nextLine().trim();
				if (msg.length() > 3) {
					messages.add(msg);
				}
			}
		}
		return new LogFactory(level, messages);
	}

	private LogFactoryProvider() {
	}
}
