package dao;

import dao.admin.PayMentStaticsDAO;
import dao.user.PaymentDAO;
import dto.user.MoviePaymentDTO;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.sql.Date;

public class PaymentDAOTest {

	private final static int ADD_SUCCESS = 1;

	@Test
	public void getPaymentStaticsTest() {
		PayMentStaticsDAO.getInstance().list();
	}

	@Test
	public void getPaymentWays() {
		PaymentDAO.getInstance().list();
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
		int result = PaymentDAO.getInstance().addPaymet(moviePaymentDTO);

		// then
		Assert.assertEquals(result, ADD_SUCCESS);
	}
}
