package dao.user;

import dto.user.MoviePaymentDTO;
import oracle.connect.JDBCManager;
import org.apache.commons.lang3.StringUtils;

public class MoviePaymentDAO implements DAO {

	private static MoviePaymentDAO instance;

	private MoviePaymentDAO() {
	}

	public static MoviePaymentDAO getInstance() {
		if (instance == null) {
			instance = new MoviePaymentDAO();
		}
		return instance;
	}

	public void list() {
		final String query = "SELECT " +
			"    MOVIE_PAYMENT_CODE, " +
			"    MYUSER_ID, " +
			"    MOVIE_CODE, " +
			"    PAYMENT_CODE, " +
			"    PAYMENT_DATE " +
			"FROM " +
			"    MOVIE_PAYMENT ";
		JDBCManager
			.getInstance()
			.queryForList(query, MoviePaymentDTO.class)
			.forEach(moviePaymentDTO -> {
				System.out.println(moviePaymentDTO.toString());
			});
	}

	public void listMe(String id) {

		final String query = "SELECT " +
			"    mp.movie_payment_code, " +
			"    mp.myuser_id, " +
			"    m.movie_title, " +
			"    m.movie_age, " +
			"    p.payment_way, " +
			"    mp.payment_date " +
			"FROM " +
			"    movie_payment mp " +
			"JOIN " +
			"    movie m ON mp.movie_code = m.movie_code " +
			"JOIN " +
			"    payment p ON mp.payment_code = p.payment_code " +
			"WHERE " +
			"    mp.myuser_id = " + StringUtils.wrap(id, "'");

		JDBCManager
			.getInstance()
			.queryForMaps(query, new String[] {"mp.movie_payment_code",
				"mp.myuser_id",
				"m.movie_title",
				"m.movie_age",
				"p.payment_way",
				"mp.payment_date"
			})
			.forEach(stringObjectMap -> {
				stringObjectMap.forEach((key, value) -> System.out.print(key + " : " + value + "\t"));
				System.out.println();
			});
	}
}
