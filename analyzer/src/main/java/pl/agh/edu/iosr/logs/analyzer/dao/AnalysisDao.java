package pl.agh.edu.iosr.logs.analyzer.dao;

import pl.agh.edu.iosr.logs.analyzer.RecordIterator;

/**
 * An interface used for getting analysis data from data warehouses.
 */
public interface AnalysisDao {
	RecordIterator getLogLevelSum();

	RecordIterator getLogLevelByHour();

	RecordIterator getLogLevelByDay();

	RecordIterator getLogLevelByDate();
	
	RecordIterator getLogClassByDay();

	void setTableName(String tableName);

	RecordIterator getLogClassOverall();

	RecordIterator getLogMessageLength();
}
