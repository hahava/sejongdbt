package feat.actor;

import config.JDBCManager;

import java.util.List;

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

	public List<MovieActorDTO> selectMovieActors() {
		final String query = "select " +
			"ACTOR_CODE, " +
			"MOVIE_CODE, " +
			"ACTOR_ROLE " +
			"	from " +
			"MOVIE_ACTOR";

		return JDBCManager
			.getInstance()
			.queryForList(query, MovieActorDTO.class);
	}
}
