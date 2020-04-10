package feat.payment;

import oracle.connect.JDBCManager;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;

public class MoviePaymentDAO {

	private static MoviePaymentDAO instance;

	private MoviePaymentDAO() {
	}

	public static MoviePaymentDAO getInstance() {
		if (instance == null) {
			instance = new MoviePaymentDAO();
		}
		return instance;
	}

	public List<MoviePaymentDTO> selectMoviePayments() {
		final String query = "SELECT " +
			"    MOVIE_PAYMENT_CODE, " +
			"    MYUSER_ID, " +
			"    MOVIE_CODE, " +
			"    PAYMENT_CODE, " +
			"    PAYMENT_DATE " +
			"FROM " +
			"    MOVIE_PAYMENT ";

		return JDBCManager
			.getInstance()
			.queryForList(query, MoviePaymentDTO.class);
	}

	public List<Map<String, Object>> selectMyMoviePayments(String id) {

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

		return JDBCManager
			.getInstance()
			.queryForMaps(query, new String[] {"mp.movie_payment_code",
				"mp.myuser_id",
				"m.movie_title",
				"m.movie_age",
				"p.payment_way",
				"mp.payment_date"
			});
	}
}
