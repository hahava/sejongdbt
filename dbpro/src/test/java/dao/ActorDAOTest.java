package dao;

import dao.admin.ActorDAO;
import dto.admin.ActorDTO;
import oracle.connect.JDBCManager;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

public class ActorDAOTest {

	@Test
	public void getActorsTest() {
		ActorDAO.getInstance().list();
	}

	@Test
	public void getActorByMovieTitleTest() {
		final String movieTitle = "'겟 아웃'";
		final String query = new StringBuilder(
			"SELECT AC.ACTOR_CODE AS ACTOR_CODE, AC.ACTOR_NAME AS ACTOR_NAME, AC.ACTOR_BIRTH AS ACTOR_BIRTH, AC.ACTOR_GENDER AS ACTOR_GENDER")
			.append(StringUtils.LF)
			.append("FROM MOVIE_ACTOR MA")
			.append(StringUtils.LF)
			.append("JOIN")
			.append(StringUtils.LF)
			.append("MOVIE MV ON MV.MOVIE_CODE = MA.MOVIE_CODE")
			.append(StringUtils.LF)
			.append("JOIN")
			.append(StringUtils.LF)
			.append("ACTOR AC ON AC.ACTOR_CODE = MA.ACTOR_CODE")
			.append(StringUtils.LF)
			.append("WHERE MOVIE_TITLE = ")
			.append(movieTitle)
			.toString();

		JDBCManager
			.getInstance()
			.queryForList(query, ActorDTO.class)
			.forEach(actorDTO -> System.out.println(actorDTO.toString()));
	}

}
