package kr.ac.sejong.menu;

import kr.ac.sejong.auth.Account;
import kr.ac.sejong.auth.AuthLevel;

public class MainView {
	public static void show() {
		System.out.println("원하는 정보를 보여드립니다.!");
		if (Account.getUser().getLevel() == AuthLevel.ADMIN) {
			showUserMenu();
		} else {
			showAdminMenu();
		}
	}

	private static void showAdminMenu() {
		System.out.println("1. 유저 정보");
		System.out.println("2. 영화 정보");
		System.out.println("3. 결제 통계 정보");
		System.out.println("4. 광고 정보");
		System.out.println("5. 영화에 삽입 된 정보");
		System.out.println("6. 직원 정보");
		System.out.println("7. 직원 업무 내역");
		System.out.println("8. 배우 정보");
		System.out.println("9. 영화에 출연한 배우 정보");
		System.out.println("10. 회원평점정보");
		System.out.println("11. 결제 정보");
		System.out.println("12. 영화 결제 정보");
		System.out.println("13. 간식 종류");
		System.out.println("14. 회원들이 주문한 간식 종류");
		System.out.println("15. 로그인 변경");
		System.out.println("16. 데이터 초기화");
		System.out.println("17. 프로그램 종료");
		System.out.println("18. 직원관련 검색");
		System.out.println("19. 우수회원");
		System.out.println("20. 영화 통계");
	}

	private static void showUserMenu() {
		System.out.println("1. 나의 정보");
		System.out.println("2. 영화 정보");
		System.out.println("3. 영화 평점 정보");
		System.out.println("4. 나의 평점 정보");
		System.out.println("5. 결제 관련");
		System.out.println("6. 나의 결제 내역");
		System.out.println("7. 스낵정보");
		System.out.println("8. 나의 스낵주문 내역");
		System.out.println("9. 로그인 변경");
		System.out.println("10. 프로그램 종료");
	}

}
