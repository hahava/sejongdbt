package dao.admin;

import dto.admin.PaymentStaticsDTO;
import oracle.connect.JDBCManager;

public class PayMentStaticsDAO {

	private static PayMentStaticsDAO instance;

	private PayMentStaticsDAO() {
	}

	public static PayMentStaticsDAO getInstance() {
		if (instance == null) {
			instance = new PayMentStaticsDAO();
		}
		return instance;
	}

	public void list() {
		final String query = "SELECT " +
			"	PAYMENT_DATE, " +
			"	PAYMENT_SUM, " +
			"	PAYMENT_COUNT, " +
			"	MYUSER_COUNT " +
			"FROM " +
			"	PAYMENT_STATICS";

		JDBCManager
			.getInstance()
			.queryForList(query, PaymentStaticsDTO.class)
			.forEach(paymentStaticsDTO -> {
				System.out.println(paymentStaticsDTO);
			});
	}
}
