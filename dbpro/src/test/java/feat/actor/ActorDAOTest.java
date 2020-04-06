package feat.actor;

import feat.actor.ActorDAO;
import org.junit.Ignore;
import org.junit.Test;

public class ActorDAOTest {

	@Ignore
	@Test
	public void getActorsTest() {
		ActorDAO.getInstance().selectActors();
	}

	@Ignore
	@Test
	public void getActorByMovieTitleTest() {
		ActorDAO.getInstance().selectActorByMovieTitle("겟 아웃");
	}

}
