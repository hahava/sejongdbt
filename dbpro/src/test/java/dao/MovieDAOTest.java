package dao;

import dao.admin.MovieAdDAO;
import dao.user.MovieDAO;
import org.junit.Test;

public class MovieDAOTest {

	@Test
	public void getMovieAdTest() {
		MovieAdDAO.getInstance().list();
	}

	@Test
	public void getMoviesTest() {
		MovieAdDAO.getInstance().list();
	}

	@Test
	public void getMovieInfoTest(){
		MovieDAO.getInstance().getMovieInfo("어느날 그가 죽었다");
	}

	@Test
	public void getMoviesByActorNameTest(){
		MovieDAO.getInstance().getMoviesByActor("박지성");
	}
}
