package pl.agh.edu.iosr.logs.analyzer;

import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * Analyzer program entry point.
 */
public class Analyzer {
	public static void main(String[] args) {
		Injector injector = Guice.createInjector(new AnalyzerModule());
		LogsAnalyzer analyzer = injector.getInstance(LogsAnalyzer.class);
		analyzer.analyze();
	}
}
