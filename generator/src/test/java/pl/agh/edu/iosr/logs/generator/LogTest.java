package pl.agh.edu.iosr.logs.generator;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.mockito.Mockito;

public class LogTest {

	@Test
	public void testDoLog() {
		Log log = new Log(Level.INFO, "A log");
		Logger logger = Mockito.mock(Logger.class);
		log.doLog(logger);
		Mockito.verify(logger).log(Level.INFO, "A log");
	}
}
