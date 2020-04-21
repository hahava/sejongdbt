package kr.ac.sejong.dao;

import kr.ac.sejong.feat.actor.MovieActorDAO;
import org.junit.Test;

public class MovieActorDAOTest {

	@Test
	public void getMovieActorTest() {
		MovieActorDAO.getInstance().selectMovieActors();
	}

}
