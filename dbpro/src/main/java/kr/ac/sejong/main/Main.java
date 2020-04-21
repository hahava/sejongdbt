package kr.ac.sejong.main;

import kr.ac.sejong.auth.Account;
import kr.ac.sejong.menu.MainView;
import kr.ac.sejong.menu.MenuSelector;
import kr.ac.sejong.util.PropertiesWrapper;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;

import static kr.ac.sejong.util.ConsoleUtil.close;
import static kr.ac.sejong.util.ConsoleUtil.readString;

/**
 * @author kalin
 * @date 2017. 6. 8.
 */
public class Main {

	public static void initialize() {
		try {
			Class.forName(PropertiesWrapper.getInstance().getProperty("jdbc.driver"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws IOException {
		initialize();

		while (Account.isLogin() == false) {
			String id = readString("아이디를 입력해주세요.");
			String pw = readString("패스워드를 입력해주세요.");
			Account.doLogin(id, pw);
		}

		while (true) {
			MainView.show();
			String requestMenu = readString("원하는 메뉴를 선택해주세요");

			if (StringUtils.equals("프로그램 종료", requestMenu)) {
				break;
			}

			MenuSelector.getInstance().select(requestMenu);
		}
		destroy();
	}

	public static void destroy() {
		try {
			close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
