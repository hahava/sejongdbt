package kr.ac.sejong.config;

import kr.ac.sejong.menu.MenuSelector;
import org.junit.Test;
import kr.ac.sejong.util.PropertiesWrapper;

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