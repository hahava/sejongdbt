package config;

import org.junit.Test;
import util.PropertiesWrapper;

public class JDBCLoadTest {

	@Test
	public void loadJDBCDriverTest() {
		System.out.println(PropertiesWrapper.getInstance().getProperty("jdbc.driver"));
	}

}