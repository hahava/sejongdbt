package dao;

import dto.admin.ActorDTO;
import oracle.connect.JDBCManager;
import org.junit.Test;

public class ActorDAOTest {

	@Test
	public void getActorsTest() {
		JDBCManager
			.getInstance()
			.queryForList("SELECT ACTOR_CODE, ACTOR_NAME, ACTOR_GENDER, ACTOR_BIRTH FROM ACTOR", ActorDTO.class)
			.forEach(actorDTO -> System.out.println(actorDTO.toString()));
	}

}
