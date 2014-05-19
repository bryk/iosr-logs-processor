package pl.agh.edu.iosr.logs.analyzer.dao.hive;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class HiveAnalysisDaoTest {
	private HiveAnalysisDao dao;
	private HiveJdbcClient jdbcClient;

	@Before
	public void setUp() throws Exception {
		jdbcClient = Mockito.mock(HiveJdbcClient.class);
		dao = new HiveAnalysisDao(jdbcClient);
	}

	@Test
	public void testGetLogLevelByHour() {
		String sql = "SELECT HOUR(timestamp_millis) AS hr, level, COUNT(level) FROM iosr GROUP BY HOUR(timestamp_millis), level ORDER BY hr, level";
		Mockito.doReturn(null).when(jdbcClient).executeSelect(sql);
		dao.getLogLevelByHour();
		Mockito.verify(jdbcClient).executeSelect(sql);
	}

	@Test
	public void testGetLogLevelByDay() {
		String sql = "SELECT DAY(timestamp_millis) AS logday, level, COUNT(level) FROM iosr GROUP BY DAY(timestamp_millis), level ORDER BY logday, level";
		Mockito.doReturn(null).when(jdbcClient).executeSelect(sql);
		dao.getLogLevelByDay();
		Mockito.verify(jdbcClient).executeSelect(sql);

	}

	@Test
	public void testGetLogLevelByDate() {
		String sql = "SELECT DATE(timestamp_millis) AS logdate, level, COUNT(level) FROM iosr GROUP BY DATE(timestamp_millis), level ORDER BY logdate, level";
		Mockito.doReturn(null).when(jdbcClient).executeSelect(sql);
		dao.getLogLevelByDate();
		Mockito.verify(jdbcClient).executeSelect(sql);
	}
}
