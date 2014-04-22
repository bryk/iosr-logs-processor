package pl.agh.edu.iosr.logs.analyzer;

import java.sql.SQLException;

import com.google.inject.Inject;

import pl.agh.edu.iosr.logs.analyzer.jdbc.HiveJdbcClient;

/**
 * Analyzes logs from Hive.
 */
public class LogsAnalyzer {
	private HiveJdbcClient hiveJdbcClient;

	@Inject
	public LogsAnalyzer(HiveJdbcClient hiveJdbcClient) {
		this.hiveJdbcClient = hiveJdbcClient;
	}

	public void analyze() {
		System.out.println("Hello Analyze");
		try {
			hiveJdbcClient.test();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
