package dao;

import dao.admin.PayMentStaticsDAO;
import dao.user.PaymentDAO;
import org.junit.Test;

public class PaymentDAOTest {
	@Test
	public void getPaymentStaticsTest() {
		PayMentStaticsDAO.getInstance().list();
	}

	@Test
	public void getPaymentWays() {
		PaymentDAO.getInstance().list();
	}

	@Test
	public void deletePayment(){
		PaymentDAO.getInstance().deletePayment(2);
	}
}
