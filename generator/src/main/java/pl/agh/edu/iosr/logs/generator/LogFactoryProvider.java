package pl.agh.edu.iosr.logs.generator;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

import org.apache.log4j.Level;

public class LogFactoryProvider {
	public static LogFactory createLogFactory(Level level, InputStream messagesStream) {
		ArrayList<String> messages = new ArrayList<String>();
		Scanner scanner = null;
		try {
			scanner = new Scanner(messagesStream);
			while (scanner.hasNextLine()) {
				String msg = scanner.nextLine().trim();
				if (msg.length() > 3) {
					messages.add(msg);
				}
			}
		} finally {
			if (scanner != null)
				scanner.close();
		}
		return new LogFactory(level, messages);
	}

	private LogFactoryProvider() {
	}
}
