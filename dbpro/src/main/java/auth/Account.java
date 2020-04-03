package auth;

import consts.UserLevel;
import dao.user.MyuserDAO;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

public class Account {

	private static Account instance;
	private static UserLevel level;

	public static void doLogin(String id, String passWord) {
		if (isLogin()) {
			return;
		}

		Map<String, Object> result = MyuserDAO.getInstance().login(id, passWord);
		if (result.size() == 0) {
			return;
		}

		if (StringUtils.equals(id, "admin")) {
			level = UserLevel.ADMIN;
		} else {
			level = UserLevel.USER;
		}

		instance = new Account();
	}

	public static boolean isLogin() {
		if (instance == null) {
			return false;
		}
		return true;
	}

	public static UserLevel getUserLevel() {
		return level;
	}
}
