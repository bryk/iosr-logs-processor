package pl.agh.edu.iosr.logs.analyzer.dao.hive;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.google.inject.Inject;

public class HiveJdbcClient {
	private static final String DRIVER_NAME = "org.apache.hive.jdbc.HiveDriver";
	private Connection connection;
	private Logger logger = Logger.getLogger(HiveJdbcClient.class);

	@Inject
	public HiveJdbcClient() {
		try {
			Class.forName(DRIVER_NAME);
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException(e);
		}
		try {
			connection = DriverManager.getConnection("jdbc:hive2://localhost.localdomain:10000",
					"cloudera", "cloudera");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void execute(String sql) {
		try {
			logger.info("Executing Hive query: " + sql);
			connection.createStatement().execute(sql);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public ResultSet executeSelect(String sql) {
		try {
			logger.info("Executing Hive query: " + sql);
			return connection.createStatement().executeQuery(sql);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
