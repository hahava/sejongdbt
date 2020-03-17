package dao;

import dao.admin.ActorDAO;
import org.junit.Test;

public class ActorDAOTest {

	@Test
	public void getActorsTest() {
		ActorDAO.getInstance().list();
	}

}
