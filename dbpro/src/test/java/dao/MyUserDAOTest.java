package dao;

import dao.user.MyuserDAO;
import dao.user.MyuserSnackOrderDAO;
import org.junit.Assert;
import org.junit.Test;

import javax.management.ObjectName;
import java.util.Map;

public class MyUserDAOTest {

	@Test
	public void getMyUserTest() {
		MyuserDAO.getInstance().list();
	}

	@Test
	public void selectUserByUserIdTest() {
		// given
		String userId = "eeww95";

		// when
		MyuserDAO.getInstance().selectUserInfo(userId);
	}

	@Test
	public void selectUserSnackOrders() {
		MyuserSnackOrderDAO.getInstance().list();
	}

	@Test
	public void selectSnackOrdersTest() {
		// given
		String userId = "eeww95";

		// when
		MyuserSnackOrderDAO.getInstance().selectSnackOrders(userId);
	}

	@Test
	public void selectMostOrderedMemberTest() {
		MyuserSnackOrderDAO.getInstance().selectMostOrderedMember();
	}

	@Test
	public void selectMinSnackOrderTest() {
		// given
		int minPrice = 2000;

		// when
		MyuserSnackOrderDAO.getInstance().selectMinPriceOrders(minPrice);
	}

	@Test
	public void selectUserIdAndPwTest() {
		// given
		String userId = "admin";
		String userPw = "root123";

		// when
		Map<String, Object> result = MyuserDAO.getInstance().login(userId, userPw);

		// then
		Assert.assertEquals(result.size(), 1);
	}
}
