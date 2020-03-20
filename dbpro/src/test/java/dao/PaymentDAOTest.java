package dao;

import dao.admin.PayMentStaticsDAO;
import org.junit.Test;

public class PaymentDAOTest {
	@Test
	public void getPaymentStaticsTest() {
		PayMentStaticsDAO.getInstance().list();
	}
}
