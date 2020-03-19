package dao;

import dao.admin.MovieAdDAO;
import dao.user.MovieDAO;
import oracle.connect.JDBCManager;
import org.junit.Test;
import org.junit.runner.RunWith;

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

	@Test
	public void delete(){
	}
}
