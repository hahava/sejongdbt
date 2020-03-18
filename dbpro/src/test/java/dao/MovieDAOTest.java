package dao;

import dao.admin.MovieAdDAO;
import org.junit.Test;

public class MovieDAOTest {

	@Test
	public void getMovieAdTest() {
		MovieAdDAO.getInstance().list();
	}

}
