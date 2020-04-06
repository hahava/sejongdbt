package feat.actor;

import oracle.connect.JDBCManager;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class ActorDAO {

	private static ActorDAO instance;

	private ActorDAO() {
	}

	public static ActorDAO getInstance() {
		if (instance == null) {
			instance = new ActorDAO();
		}
		return instance;
	}

	// 등록된 배우를 전부 출력한다.
	public List<ActorDTO> selectActors() {
		final String query = "SELECT ACTOR_CODE, " +
			"	ACTOR_NAME, " +
			"	ACTOR_GENDER, " +
			"	ACTOR_BIRTH " +
			"FROM ACTOR";

		return JDBCManager
			.getInstance()
			.queryForList(query, ActorDTO.class);
	}

	// 특정 영화에 출연한 배우 검색
	public List<ActorDTO> selectActorByMovieTitle(String movieTitle) {

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
			.append("\'" + movieTitle + "\'")
			.toString();

		return JDBCManager.getInstance().queryForList(query, ActorDTO.class);
	}
}
