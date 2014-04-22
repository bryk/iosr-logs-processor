package pl.agh.edu.iosr.logs.analyzer.dao.hive;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.google.inject.Inject;

public class HiveJdbcClient {
	private static final String DRIVER_NAME = "org.apache.hive.jdbc.HiveDriver";
	private Connection connection;

	@Inject
	public HiveJdbcClient() {
		try {
			Class.forName(DRIVER_NAME);
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException(e);
		}
		try {
			connection = DriverManager.getConnection(
					"jdbc:hive2://localhost.localdomain:10000/default", "", "");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void execute(String sql) {
		try {
			connection.createStatement().execute(sql);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public ResultSet executeSelect(String sql) {
		try {
			return connection.createStatement().executeQuery(sql);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
