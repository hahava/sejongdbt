package main;

import auth.Account;
import menu.MainView;
import menu.MenuSelector;
import org.apache.commons.lang3.StringUtils;
import util.ConsoleUtil;

import java.io.IOException;

/**
 * @author kalin
 * @date 2017. 6. 8.
 */
public class Main {

	public static void main(String[] args) throws IOException {

		while (Account.isLogin() == false) {
			String id = ConsoleUtil.readString("아이디를 입력해주세요.");
			String pw = ConsoleUtil.readString("패스워드를 입력해주세요.");
			Account.doLogin(id, pw);
		}

		while (true) {
			MainView.show();
			String requestCode = ConsoleUtil.readString("원하는 메뉴를 선택해주세요");
			if (StringUtils.equals("프로그램 종료", requestCode)) {
				System.exit(0);
			}
			MenuSelector.getInstance().select(requestCode);
		}
	}
}
