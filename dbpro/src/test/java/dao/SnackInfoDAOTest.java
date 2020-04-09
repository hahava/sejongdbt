package dao;

import feat.snack.SnackInfoDAO;
import org.junit.Test;

public class SnackInfoDAOTest {

	@Test
	public void getSnackInfoTest() {
		SnackInfoDAO.getInstance().selectSnacks();
	}

}
