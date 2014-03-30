package pl.agh.edu.iosr.logs.generator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import org.apache.log4j.Level;

public class LogFactoryProvider {
	public static LogFactory createLogFactory(Level level, File messagesFile) {
		ArrayList<String> messages = new ArrayList<>();
		try (Scanner scanner = new Scanner(messagesFile)) {
			while (scanner.hasNextLine()) {
				String msg = scanner.nextLine().trim();
				if (msg.length() > 3) {
					messages.add(msg);
				}
			}
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
		return new LogFactory(level, messages);
	}

	private LogFactoryProvider() {
	}
}
