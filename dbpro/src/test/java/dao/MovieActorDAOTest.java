package dao;

import feat.actor.MovieActorDAO;
import org.junit.Test;

public class MovieActorDAOTest {

	@Test
	public void getMovieActorTest() {
		MovieActorDAO.getInstance().selectMovieActors();
	}

}
