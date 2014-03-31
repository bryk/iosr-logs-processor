package pl.agh.edu.iosr.logs.generator;

import java.io.InputStream;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.PosixParser;
import org.apache.log4j.Level;

/**
 * Program generating random logs with various levels.
 */
public class Generator {
	private static final double DEFAULT_INFO_LPS = 10;
	private static final double DEFAULT_WARN_LPS = 3.2;
	private static final double DEFAULT_DEBUG_LPS = 2;
	private static final double DEFAULT_ERROR_LPS = 0.1234;

	public static void main(String[] args) {
		CommandLineParser parser = new PosixParser();
		Options options = new Options();
		options.addOption("i", "info-lps", true, "INFO logs per second, defaults to " + DEFAULT_INFO_LPS);
		options.addOption("w", "warn-lps", true, "WARN logs per second, defaults to " + DEFAULT_WARN_LPS);
		options.addOption("d", "debug-lps", true, "DEBUG logs per second, defaults to " + DEFAULT_DEBUG_LPS);
		options.addOption("e", "error-lps", true, "ERROR logs per second, defaults to " + DEFAULT_ERROR_LPS);
		options.addOption("h", "help", false, "Show this help");

		double infoLps = DEFAULT_INFO_LPS;
		double warnLps = DEFAULT_WARN_LPS;
		double debugLps = DEFAULT_DEBUG_LPS;
		double errorLps = DEFAULT_ERROR_LPS;
		try {
			CommandLine line = parser.parse(options, args);

			if (line.hasOption("info-lps")) {
				infoLps = Double.parseDouble(line.getOptionValue("info-lps"));
			}
			if (line.hasOption("warn-lps")) {
				warnLps = Double.parseDouble(line.getOptionValue("warn-lps"));
			}
			if (line.hasOption("debug-lps")) {
				debugLps = Double.parseDouble(line.getOptionValue("debug-lps"));
			}
			if (line.hasOption("error-lps")) {
				errorLps = Double.parseDouble(line.getOptionValue("error-lps"));
			}
			if (line.hasOption("help")) {
				HelpFormatter formatter = new HelpFormatter();
				formatter.printHelp(Generator.class.getCanonicalName(), options);
				System.exit(0);
			}
		} catch (org.apache.commons.cli.ParseException e) {
			throw new RuntimeException(e);
		} catch (IllegalArgumentException e) {
			System.out.println("Invalid value: " + e.getMessage());
		}
		Generator generator = new Generator();
		generator.start(infoLps, warnLps, debugLps, errorLps);
	}

	private void start(double infoLps, double warnLps, double debugLps, double errorLps) {
		System.out.println(String.format("Starting logging with INFO: %f, WARN: %f, DEBUG: %f, ERROR: %f", infoLps,
				warnLps, debugLps, errorLps));
		runLogger(Level.INFO, infoLps);
		runLogger(Level.WARN, warnLps);
		runLogger(Level.DEBUG, debugLps);
		runLogger(Level.ERROR, errorLps);
	}

	private void runLogger(Level level, double logsPerSecond) {
		InputStream messagesStream = this.getClass().getResourceAsStream("/romeoandjuliet.txt");
		LogFactory logFactory = LogFactoryProvider.createLogFactory(level, messagesStream);
		final LogsGenerator logsGenerator = new LogsGenerator(logsPerSecond, logFactory);
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				logsGenerator.startLogging();
			}
		});

		thread.start();
	}
}
