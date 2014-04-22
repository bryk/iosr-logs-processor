package pl.agh.edu.iosr.logs.analyzer;

import pl.agh.edu.iosr.logs.analyzer.jdbc.HiveJdbcClient;

import com.google.inject.AbstractModule;

public class AnalyzerModule extends AbstractModule {
	@Override
	protected void configure() {
		bind(HiveJdbcClient.class);
	}
}
