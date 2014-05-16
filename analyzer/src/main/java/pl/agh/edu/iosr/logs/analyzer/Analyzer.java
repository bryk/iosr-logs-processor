package pl.agh.edu.iosr.logs.analyzer;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.PosixParser;

import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * Analyzer program entry point.
 */
public class Analyzer {
	private static final String DEFAULT_TABLE_NAME = "iosr";

	public static void main(String[] args) {
		CommandLineParser parser = new PosixParser();
		Options options = new Options();
		options.addOption("t", "table-name", true, "Hive table name, defaults to "
				+ DEFAULT_TABLE_NAME);
		options.addOption("h", "help", false, "Show this help");
		
		String tableName = DEFAULT_TABLE_NAME;
		try {
			CommandLine line = parser.parse(options, args);

			if (line.hasOption("table-name")) {
				tableName = line.getOptionValue("table-name");
			}
			if (line.hasOption("help")) {
				HelpFormatter formatter = new HelpFormatter();
				formatter.printHelp(Analyzer.class.getCanonicalName(), options);
				System.exit(0);
			}
		} catch (org.apache.commons.cli.ParseException e) {
			throw new RuntimeException(e);
		} catch (IllegalArgumentException e) {
			System.out.println("Invalid value: " + e.getMessage());
		}
		Injector injector = Guice.createInjector(new AnalyzerModule());
		LogsAnalyzer analyzer = injector.getInstance(LogsAnalyzer.class);
		analyzer.setTableName(tableName);
		analyzer.analyze();
	}
}
