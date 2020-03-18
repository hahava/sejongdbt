package dao.admin;

import dto.admin.ActorDTO;
import oracle.connect.JDBCManager;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class ActorDAO implements DAO {

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
	public void list() {
		final String query = "SELECT ACTOR_CODE, ACTOR_NAME, ACTOR_GENDER, ACTOR_BIRTH FROM ACTOR";
		JDBCManager
			.getInstance()
			.queryForList(query, ActorDTO.class)
			.forEach(actorDTO -> System.out.println(actorDTO.toString()));
	}

	// 특정 영화에 출연한 배우 검색
	public void getActor(String movieTitle) {

		System.out.println("특정 영화에 출연한 배우를 검색합니다. 영화 명을 입력하세요.");

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

		List<ActorDTO> actors = JDBCManager.getInstance().queryForList(query, ActorDTO.class);
		if (actors.size() == 0) {
			System.out.println("영화정보가 없거나 배우정보가 존재하지 않습니다.");
		} else {
			actors.stream().forEach(actorDTO -> {
				System.out.println("배우명 : " + actorDTO.getActorName() + "\t" + "성별 : " + actorDTO
					.getActorGender() + " \t " + "생년월일 : " + actorDTO.getActorBirth());
			});
		}
	}
}
