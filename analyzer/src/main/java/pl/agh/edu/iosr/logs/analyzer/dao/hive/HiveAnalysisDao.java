package pl.agh.edu.iosr.logs.analyzer.dao.hive;

import org.apache.log4j.Logger;

import pl.agh.edu.iosr.logs.analyzer.RecordIterator;
import pl.agh.edu.iosr.logs.analyzer.dao.AnalysisDao;

import com.google.inject.Inject;

public class HiveAnalysisDao implements AnalysisDao {
	private HiveJdbcClient jdbcClient;

	private String tableName = "iosr";
	private Logger logger = Logger.getLogger(HiveAnalysisDao.class);
	
	@Inject
	public HiveAnalysisDao(HiveJdbcClient jdbcClient) {
		this.jdbcClient = jdbcClient;

	}

	public RecordIterator getLogLevelSum() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecordIterator getLogLevelByHour() {
		logger.debug("getLogLevelByHour");
		String q = String
				.format("SELECT HOUR(timestamp_millis) AS hr, level, COUNT(level) FROM %s GROUP BY HOUR(timestamp_millis), level ORDER BY hr, level",
						tableName);
		return new RecordIterator(this.jdbcClient.executeSelect(q));
	}

	@Override
	public RecordIterator getLogLevelByDay() {
		logger.debug("getLogLevelByDay");
		String q = String
				.format("SELECT DAY(timestamp_millis) AS logday, level, COUNT(level) FROM %s GROUP BY DAY(timestamp_millis), level ORDER BY logday, level",
						tableName);
		return new RecordIterator(this.jdbcClient.executeSelect(q));
	}

	@Override
	public RecordIterator getLogLevelByDate() {
		logger.debug("getLogLevelByDate");
		String q = String
				.format("SELECT DATE(timestamp_millis) AS logdate, level, COUNT(level) FROM %s GROUP BY DATE(timestamp_millis), level ORDER BY logdate, level",
						tableName);
		return new RecordIterator(this.jdbcClient.executeSelect(q));
	}

	@Override
	public RecordIterator getLogClassByDay() {
		logger.debug("getLogClassByDay");
		String q = String
				.format("SELECT DAY(timestamp_millis) AS logday, class, COUNT(class) FROM %s GROUP BY DAY(timestamp_millis), class ORDER BY logday, class",
						tableName);
		return new RecordIterator(this.jdbcClient.executeSelect(q));
	}

	@Override
	public RecordIterator getLogClassOverall() {
		logger.debug("getLogClassOverall");
		String q = String.format(
				"SELECT class, COUNT(class) FROM %s GROUP BY class ORDER BY class", tableName);
		return new RecordIterator(this.jdbcClient.executeSelect(q));
	}

	@Override
	public RecordIterator getLogMessageLength() {
		logger.debug("getLogMessageLength");
		String q = String
				.format("SELECT IF(message IS NULL, -1, LENGTH(message)) AS len, COUNT(IF(message IS NULL, -1, LENGTH(message))) "
						+ "AS cnt FROM %s GROUP BY IF(message IS NULL, -1, LENGTH(message)) ORDER BY len",
						tableName);
		return new RecordIterator(this.jdbcClient.executeSelect(q));
	}

	@Override
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
}
