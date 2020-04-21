package kr.ac.sejong.feat.advertisement;

import kr.ac.sejong.config.JDBCManager;

import java.util.List;

public class MovieAdDAO {

	private MovieAdDAO() {
	}

	private static MovieAdDAO instance;

	public static MovieAdDAO getInstance() {
		if (instance == null) {
			instance = new MovieAdDAO();
		}
		return instance;
	}

	public List<MovieAdDTO> selectMovieADs() {
		final String query = "SELECT " +
			"	CNT, " +
			"	MOVIE_CODE, " +
			"	AD_TITLE " +
			"FROM " +
			"	MOVIE_AD";

		return JDBCManager
			.getInstance()
			.queryForList(query, MovieAdDTO.class);
	}

}
