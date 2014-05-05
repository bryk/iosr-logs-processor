package pl.agh.edu.iosr.logs.analyzer;

import pl.agh.edu.iosr.logs.analyzer.dao.AnalysisDao;
import pl.agh.edu.iosr.logs.analyzer.dao.hive.HiveAnalysisDao;
import pl.agh.edu.iosr.logs.analyzer.dao.hive.HiveJdbcClient;

import com.google.inject.AbstractModule;

public class AnalyzerModule extends AbstractModule {
	@Override
	protected void configure() {
		bind(HiveJdbcClient.class);
		bind(AnalysisDao.class).to(HiveAnalysisDao.class);
	}
}
