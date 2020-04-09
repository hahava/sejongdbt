package feat.payment;

import dto.user.MoviePaymentDTO;
import oracle.connect.JDBCManager;

import java.util.List;

public class PaymentDAO {

	private static PaymentDAO instance;

	private PaymentDAO() {
	}

	public static PaymentDAO getInstance() {
		if (instance == null) {
			instance = new PaymentDAO();
		}
		return instance;
	}

	// 결제 방법을 출력한다.
	public List<PaymentDTO> selectPaymentWays() {
		final String query = "SELECT PAYMENT_CODE, PAYMENT_WAY FROM PAYMENT";
		return JDBCManager
			.getInstance()
			.queryForList(query, PaymentDTO.class);
	}

	// 예매 목록을 코드를 이용하여 삭제 하는 메뉴이다.
	public void deletePayment(final int movieReservationCode) {
		final String query = "DELETE " +
			"FROM " +
			"	MOVIE_PAYMENT " +
			"WHERE " +
			"	MOVIE_PAYMENT_CODE = ?";

		int result = JDBCManager
			.getInstance()
			.delete(query, new Object[] {movieReservationCode});
	}

	// 영화 수정을 할 수 있는 메뉴이다.
	public void updateMovieReservation(MoviePaymentDTO moviePaymentDTO) {

		final String query = "UPDATE " +
			"	MOVIE_PAYMENT " +
			"SET " +
			"	MOVIE_CODE = ?, " +
			"	PAYMENT_CODE = ?, " +
			"	PAYMENT_DATE = ? " +
			"WHERE " +
			"	MOVIE_PAYMENT_CODE = ?";

		JDBCManager.getInstance().update(query,
			new Object[] {
				moviePaymentDTO.getMovieCode(),
				moviePaymentDTO.getPaymentCode(),
				moviePaymentDTO.getPaymentDate(),
				moviePaymentDTO.getMoviePaymentCode()
			});
	}

	//예약할 수 있는 메뉴
	public int insertMovieReservation(MoviePaymentDTO moviePaymentDTO) {
		final String query = "INSERT " +
			"INTO " +
			"	MOVIE_PAYMENT " +
			"VALUES " +
			"	(DEFAULT,?,?,?,?)";

		int result = JDBCManager
			.getInstance()
			.insert(query, new Object[] {
				moviePaymentDTO.getMyuserId(),
				moviePaymentDTO.getMovieCode(),
				moviePaymentDTO.getPaymentCode(),
				moviePaymentDTO.getPaymentDate()
			});
		return result;
	}

}
