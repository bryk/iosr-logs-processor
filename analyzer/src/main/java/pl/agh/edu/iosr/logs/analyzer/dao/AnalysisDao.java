package pl.agh.edu.iosr.logs.analyzer.dao;

import pl.agh.edu.iosr.logs.analyzer.LogLevelAggregation;

import com.google.common.collect.ImmutableList;

/**
 * An interface used for getting analysis data from data warehouses.
 */
public interface AnalysisDao {
	ImmutableList<LogLevelAggregation> getLogLevelSum();

	ImmutableList<LogLevelAggregation> getLogLevelByHour();

	ImmutableList<LogLevelAggregation> getLogLevelByDay();

	ImmutableList<LogLevelAggregation> getLogLevelByMinute();
}
