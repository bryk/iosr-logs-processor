package pl.agh.edu.iosr.logs.analyzer;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import pl.agh.edu.iosr.logs.analyzer.dao.AnalysisDao;

import com.google.common.collect.ImmutableList;
import com.google.inject.Inject;

/**
 * Analyzes logs from Hive.
 */
public class LogsAnalyzer {
	private AnalysisDao analysisDao;
	private Logger logger = Logger.getLogger(LogsAnalyzer.class);

	@Inject
	public LogsAnalyzer(AnalysisDao analysisDao) {
		this.analysisDao = analysisDao;
	}

	public void analyze() {
		generatePlot(analysisDao.getLogLevelByHour(), "logLevelByHour", "Log level by hour",
				"Hour number", "Number of ocurrences");
		generatePlot(analysisDao.getLogLevelByDay(), "logLevelByDay", "Log level by day",
				"Day number", "Number of ocurrences");
		generatePlot(analysisDao.getLogLevelByDate(), "logLevelByDat", "Log level by date", "Date",
				"Number of ocurrences");
	}

	public void generatePlot(RecordIterator iter, String fileName, String title, String xLegend,
			String yLegend) {
		DefaultCategoryDataset data = new DefaultCategoryDataset();
		int numRows = 0;
		try (RecordIterator iterLocal = iter) {
			while (iterLocal.hasNext()) {
				numRows += 1;
				ImmutableList<Object> row = iterLocal.next();
				data.addValue(((Long) row.get(2)).doubleValue(), row.get(1).toString(), row.get(0)
						.toString());
			}
		}
		JFreeChart chart = ChartFactory.createBarChart(title, xLegend, yLegend, data,
				PlotOrientation.VERTICAL, true, true, true);
		int width = numRows * 40;
		BufferedImage img = chart.createBufferedImage(width, 600);

		File outputfile = new File(fileName + ".png");
		try {
			ImageIO.write(img, "png", outputfile);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		logger.info("Created image: " + fileName + ".png");
	}
}
