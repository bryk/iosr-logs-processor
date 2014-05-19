package pl.agh.edu.iosr.logs.analyzer;

import pl.agh.edu.iosr.logs.analyzer.dao.AnalysisDao;
import pl.agh.edu.iosr.logs.analyzer.dao.hive.HiveAnalysisDao;
import pl.agh.edu.iosr.logs.analyzer.dao.hive.HiveJdbcClient;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

public class AnalyzerModule extends AbstractModule {
	private String user;
	private String password;

	public AnalyzerModule(String user, String password) {
		this.user = user;
		this.password = password;
	}

	@Override
	protected void configure() {
		bind(HiveJdbcClient.class);
		bind(AnalysisDao.class).to(HiveAnalysisDao.class);
		bind(String.class).annotatedWith(Names.named("HivePassword")).toInstance(password);
		bind(String.class).annotatedWith(Names.named("HiveUser")).toInstance(user);
	}
}
