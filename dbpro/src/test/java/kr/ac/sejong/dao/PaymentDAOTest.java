package kr.ac.sejong.dao;

import kr.ac.sejong.feat.paymentstatics.PaymentStaticsDAO;
import kr.ac.sejong.feat.payment.MoviePaymentDAO;
import kr.ac.sejong.feat.payment.PaymentDAO;
import kr.ac.sejong.feat.payment.MoviePaymentDTO;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.sql.Date;

public class PaymentDAOTest {

	private final static int ADD_SUCCESS = 1;

	@Test
	public void getPaymentStaticsTest() {
		PaymentStaticsDAO.getInstance().selectPaymentStatics();
	}

	@Test
	public void getPaymentWays() {
		PaymentDAO.getInstance().selectPaymentWays();
	}

	@Ignore
	@Test
	public void deletePayment() {
		PaymentDAO.getInstance().deletePayment(2);
	}

	@Test
	public void addPaymentTest() {

		// given
		MoviePaymentDTO moviePaymentDTO = new MoviePaymentDTO();
		moviePaymentDTO.setMovieCode("M3");
		moviePaymentDTO.setMyuserId("kmono");
		moviePaymentDTO.setPaymentCode("P1");
		moviePaymentDTO.setPaymentDate(new Date(2020, 1, 2));

		// when
		int result = PaymentDAO.getInstance().insertMovieReservation(moviePaymentDTO);

		// then
		Assert.assertEquals(result, ADD_SUCCESS);
	}

	@Test
	public void getMoviePayments() {
		MoviePaymentDAO.getInstance().selectMoviePayments();
	}

	@Test
	public void getMoviePaymentsByUserIdTest() {
		// given
		final String userId = "eeww95";
		// when
		MoviePaymentDAO.getInstance().selectMyMoviePayments("eeww95");
	}
}
