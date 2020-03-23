package dao.admin;

import dto.admin.MovieAdDTO;
import oracle.connect.JDBCManager;

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

	public void list() {
		final String query = "SELECT " +
			"	CNT, " +
			"	MOVIE_CODE, " +
			"	AD_TITLE " +
			"FROM " +
			"	MOVIE_AD";

		JDBCManager
			.getInstance()
			.queryForList(query, MovieAdDTO.class)
			.forEach(movieAdDTO -> System.out.println(movieAdDTO.toString()));
	}

}
