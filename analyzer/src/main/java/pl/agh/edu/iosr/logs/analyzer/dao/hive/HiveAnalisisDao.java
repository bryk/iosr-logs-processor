package pl.agh.edu.iosr.logs.analyzer.dao.hive;

import java.sql.ResultSet;
import java.sql.SQLException;

import pl.agh.edu.iosr.logs.analyzer.LogLevelAggregation;
import pl.agh.edu.iosr.logs.analyzer.dao.AnalysisDao;

import com.google.common.collect.ImmutableList;
import com.google.inject.Inject;

public class HiveAnalisisDao implements AnalysisDao {
	private HiveJdbcClient jdbcClient;

	@Inject
	public HiveAnalisisDao(HiveJdbcClient jdbcClient) {
		this.jdbcClient = jdbcClient;
		ResultSet res = this.jdbcClient.executeSelect("show tables");
		try {
			if (res.next()) {
				System.out.println(res.getString(1));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public ImmutableList<LogLevelAggregation> getLogLevelSum() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ImmutableList<LogLevelAggregation> getLogLevelByHour() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ImmutableList<LogLevelAggregation> getLogLevelByDay() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ImmutableList<LogLevelAggregation> getLogLevelByMinute() {
		// TODO Auto-generated method stub
		return null;
	}

}
