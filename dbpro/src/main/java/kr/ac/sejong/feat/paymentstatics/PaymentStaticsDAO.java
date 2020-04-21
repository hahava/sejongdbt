package kr.ac.sejong.feat.paymentstatics;

import kr.ac.sejong.config.JDBCManager;

import java.util.List;

public class PaymentStaticsDAO {

	private static PaymentStaticsDAO instance;

	private PaymentStaticsDAO() {
	}

	public static PaymentStaticsDAO getInstance() {
		if (instance == null) {
			instance = new PaymentStaticsDAO();
		}
		return instance;
	}

	public List<PaymentStaticsDTO> selectPaymentStatics() {
		final String query = "SELECT " +
			"	PAYMENT_DATE, " +
			"	PAYMENT_SUM, " +
			"	PAYMENT_COUNT, " +
			"	MYUSER_COUNT " +
			"FROM " +
			"	PAYMENT_STATICS";

		return JDBCManager
			.getInstance()
			.queryForList(query, PaymentStaticsDTO.class);
	}
}
