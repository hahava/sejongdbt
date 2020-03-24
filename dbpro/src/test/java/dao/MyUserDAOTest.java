package dao;

import dao.user.MyuserDAO;
import org.junit.Test;

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

}
