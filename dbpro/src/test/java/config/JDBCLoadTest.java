package config;

import menu.MenuSelector;
import org.junit.Test;
import util.PropertiesWrapper;

public class JDBCLoadTest {

	@Test
	public void loadJDBCDriverTest() {
		System.out.println(PropertiesWrapper.getInstance().getProperty("jdbc.driver"));
	}

	@Test
	public void sout() {
		MenuSelector.getInstance().select("유저 정보2");
	}
}