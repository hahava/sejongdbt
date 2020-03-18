package dao;

import dao.admin.AdDAO;
import org.junit.Test;

public class AdDAOTest {

	@Test
	public void getAdListTest() {
		AdDAO.getInstance().list();
	}

}
