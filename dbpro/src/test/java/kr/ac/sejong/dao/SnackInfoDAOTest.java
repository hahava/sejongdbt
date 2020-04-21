package kr.ac.sejong.dao;

import kr.ac.sejong.feat.snack.SnackInfoDAO;
import org.junit.Test;

public class SnackInfoDAOTest {

	@Test
	public void getSnackInfoTest() {
		SnackInfoDAO.getInstance().selectSnacks();
	}

}
