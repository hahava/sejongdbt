package dao;

import dao.user.SnackInfoDAO;
import org.junit.Test;

public class SnackInfoDAOTest {

	@Test
	public void getSnackInfoTest() {
		SnackInfoDAO.getInstance().list();
	}

}
