package pl.agh.edu.iosr.logs.analyzer;

import java.sql.Date;

public class LogLevelAggregation {
	private String level;
	private Date date;
	private int value;

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
