package pl.agh.edu.iosr.logs.analyzer;

import pl.agh.edu.iosr.logs.analyzer.dao.AnalysisDao;

import com.google.inject.Inject;

/**
 * Analyzes logs from Hive.
 */
public class LogsAnalyzer {
	private AnalysisDao analysisDao;

	@Inject
	public LogsAnalyzer(AnalysisDao analysisDao) {
		this.analysisDao = analysisDao;
	}

	public void analyze() {
		try (RecordIterator iter = analysisDao.getLogLevelByHour()) {
			while (iter.hasNext()) {
				System.out.println(iter.next());
			}
		}
	}
}
