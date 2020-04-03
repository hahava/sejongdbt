package dao;

import dao.admin.ActorDAO;
import org.junit.Ignore;
import org.junit.Test;

public class ActorDAOTest {

	@Ignore
	@Test
	public void getActorsTest() {
		ActorDAO.getInstance().selectEmployees();
	}

	@Ignore
	@Test
	public void getActorByMovieTitleTest() {
		ActorDAO.getInstance().getActor("겟 아웃");
	}

}
