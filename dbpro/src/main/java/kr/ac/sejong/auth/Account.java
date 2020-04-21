package kr.ac.sejong.auth;

import kr.ac.sejong.feat.user.MyuserDAO;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

public class Account {

	private static Account instance;
	private static User user;

	public static User getUser() {
		return user;
	}

	public static void doLogin(String id, String passWord) {
		if (isLogin()) {
			return;
		}

		Map<String, Object> result = MyuserDAO.getInstance().login(id, passWord);
		if (result.size() == 0) {
			return;
		}

		user = new User();
		user.setId(id);

		if (StringUtils.equals(id, "admin")) {
			user.setLevel(AuthLevel.ADMIN);
		}
		user.setLevel(AuthLevel.USER);

		instance = new Account();
	}

	public static boolean isLogin() {
		if (instance == null) {
			return false;
		}
		return true;
	}

}
