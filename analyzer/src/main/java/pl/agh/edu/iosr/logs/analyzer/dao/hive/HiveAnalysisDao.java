package pl.agh.edu.iosr.logs.analyzer.dao.hive;

import pl.agh.edu.iosr.logs.analyzer.RecordIterator;
import pl.agh.edu.iosr.logs.analyzer.dao.AnalysisDao;

import com.google.inject.Inject;

public class HiveAnalysisDao implements AnalysisDao {
	private HiveJdbcClient jdbcClient;

	private static final String TABLE_NAME = "iosr";

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
				.format("SELECT HOUR(timestamp_millis) AS hr, level FROM %s GROUP BY HOUR(timestamp_millis), level order by hr, level",
						TABLE_NAME);
		return new RecordIterator(this.jdbcClient.executeSelect(q));
	}

	@Override
	public RecordIterator getLogLevelByDay() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecordIterator getLogLevelByMinute() {
		// TODO Auto-generated method stub
		return null;
	}
}
