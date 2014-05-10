package pl.agh.edu.iosr.logs.analyzer;

import java.io.Closeable;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.google.common.collect.AbstractIterator;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableList.Builder;

public class RecordIterator extends AbstractIterator<ImmutableList<Object>> implements Closeable {
	private ResultSet result;

	public RecordIterator(ResultSet result) {
		this.result = result;
	}

	@Override
	protected ImmutableList<Object> computeNext() {
		try {
			int numCols = result.getMetaData().getColumnCount();
			if (result.next()) {
				Builder<Object> builder = ImmutableList.<Object> builder();
				for (int i = 1; i < numCols + 1; i++) {
					int type = result.getMetaData().getColumnType(i);
					switch (type) {
					case java.sql.Types.DATE:
						builder.add(result.getDate(i));
						break;
					case java.sql.Types.CHAR:
					case java.sql.Types.VARCHAR:
						builder.add(result.getString(i));
						break;
					case java.sql.Types.INTEGER:
						builder.add(result.getInt(i));
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
