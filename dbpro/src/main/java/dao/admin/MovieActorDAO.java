package dao.admin;

import dto.admin.MovieActorDTO;
import oracle.connect.JDBCManager;

public class MovieActorDAO {

	private MovieActorDAO() {
	}

	private static MovieActorDAO instance;

	public static MovieActorDAO getInstance() {
		if (instance == null) {
			instance = new MovieActorDAO();
		}
		return instance;
	}

	public void list() {
		final String query = "select " +
			"ACTOR_CODE, " +
			"MOVIE_CODE, " +
			"ACTOR_ROLE " +
			"	from " +
			"MOVIE_ACTOR";

		JDBCManager
			.getInstance()
			.queryForList(query, MovieActorDTO.class)
			.forEach(movieActorDTO -> System.out.println(movieActorDTO.toString()));
	}
}
