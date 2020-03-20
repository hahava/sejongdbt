package dao;

import dao.admin.MovieActorDAO;
import org.junit.Test;

public class MovieActorDAOTest {

	@Test
	public void getMovieActorTest() {
		MovieActorDAO.getInstance().list();
	}

}
