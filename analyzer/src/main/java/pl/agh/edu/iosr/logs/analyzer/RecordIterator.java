package pl.agh.edu.iosr.logs.analyzer;

import java.io.Closeable;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.google.common.collect.AbstractIterator;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableList.Builder;

public class RecordIterator extends AbstractIterator<ImmutableList<Object>> implements Closeable {
	private ResultSet result;

	private Logger logger = Logger.getLogger(RecordIterator.class);
	
	public RecordIterator(ResultSet result) {
		this.result = result;
	}

	@Override
	protected ImmutableList<Object> computeNext() {
		try {
			int numCols = result.getMetaData().getColumnCount();
			logger.debug("Number of columns: " + numCols);
			if (result.next()) {
				Builder<Object> builder = ImmutableList.<Object> builder();
				for (int i = 1; i < numCols + 1; i++) {
					logger.debug("Column name: " + result.getMetaData().getColumnName(i));
					int type = result.getMetaData().getColumnType(i);
					switch (type) {
					case java.sql.Types.DATE:
						Date d = result.getDate(i);
						logger.debug("Result date: " + d);
						builder.add(d == null ? new Date(0) : d);
						break;
					case java.sql.Types.CHAR:
					case java.sql.Types.VARCHAR:
						String s = result.getString(i);
						logger.debug("Result string: " + s);
						builder.add(s == null ? "" : s);
						break;
					case java.sql.Types.INTEGER:
					case java.sql.Types.BIGINT:
						long l = result.getLong(i);
						logger.debug("Result number: " + l);
						builder.add(l);
						break;
					default:
						throw new RuntimeException("Illegal SQL type:" + type);
					}
				}
				return builder.build();
			} else {
				return endOfData();
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void close() {
		try {
			result.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
