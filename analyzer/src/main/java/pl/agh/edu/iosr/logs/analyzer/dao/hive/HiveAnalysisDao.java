package pl.agh.edu.iosr.logs.analyzer.dao.hive;

import pl.agh.edu.iosr.logs.analyzer.RecordIterator;
import pl.agh.edu.iosr.logs.analyzer.dao.AnalysisDao;

import com.google.inject.Inject;

public class HiveAnalysisDao implements AnalysisDao {
	private HiveJdbcClient jdbcClient;
	
	private final String tableName = "iosr";

	@Inject
	public HiveAnalysisDao(HiveJdbcClient jdbcClient) {
		this.jdbcClient = jdbcClient;

	}

	@Override
	public RecordIterator getLogLevelSum() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecordIterator getLogLevelByHour() {
		String q = String
				.format("SELECT HOUR(timestamp_millis) AS hr, level, COUNT(level) FROM %s GROUP BY HOUR(timestamp_millis), level order by hr, level",
						tableName);
		return new RecordIterator(this.jdbcClient.executeSelect(q));
	}

	@Override
	public RecordIterator getLogLevelByDay() {
		String q = String
				.format("SELECT DAY(timestamp_millis) AS logday, level, COUNT(level) FROM %s GROUP BY DAY(timestamp_millis), level order by logday, level",
						tableName);
		return new RecordIterator(this.jdbcClient.executeSelect(q));
	}

	@Override
	public RecordIterator getLogLevelByDate() {
		String q = String
				.format("SELECT DATE(timestamp_millis) AS logdate, level, COUNT(level) FROM %s GROUP BY DATE(timestamp_millis), level order by logdate, level",
						tableName);
		return new RecordIterator(this.jdbcClient.executeSelect(q));
	}
}
