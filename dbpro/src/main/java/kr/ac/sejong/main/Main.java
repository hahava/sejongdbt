package kr.ac.sejong.main;

import kr.ac.sejong.menu.MainView;
import kr.ac.sejong.menu.MenuSelector;
import kr.ac.sejong.util.PropertiesWrapper;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;

import static kr.ac.sejong.util.ConsoleUtil.close;
import static kr.ac.sejong.util.ConsoleUtil.readString;

public class Main {

	private static void initialize() {
		try {
			Class.forName(PropertiesWrapper.getInstance().getProperty("jdbc.driver"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws IOException {
		initialize();

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

	private static void destroy() {
		try {
			close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
