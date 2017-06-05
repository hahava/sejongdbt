package main;

import java.util.Scanner;

import dao.admin.*;
import dao.user.*;

public class ExecuteProject {

	public static boolean authority = false; // true인경우 관리자
	public static String id; // 로그인 세션기능

	public static void main(String[] args) {

		int num = 0;
		Scanner scanner = new Scanner(System.in);
		login();
		while (num != 10) {
			showMenu();
			num = scanner.nextInt();
			num = selectMenu(num);
		}
	}

	private static void login() {
		String pw = null;
		Scanner scanner = new Scanner(System.in);
		int num = MyuserDAO.NOLOGIN;
		MyuserDAO dao = MyuserDAO.getInstance();
		while (num == 0) {
			System.out.println("아이디와 패스워 드를 입력해주세요(id와 pass * 입력시 회원가입으로 이동)");
			id = scanner.nextLine();
			pw = scanner.nextLine();
			num = dao.login(id, pw);

			if (id.equals("*") && pw.equals("*")) {
				dao.insertMyuser();
				continue;
			} else if (num == MyuserDAO.NOLOGIN) {
				System.out.println("없는 계정이거나 잘못된 암호입니다!!!\n\n\n");
			}
		}
		if (num == MyuserDAO.ADMINLOGIN) {
			System.out.println("관리자 입니다 반갑습니다!");
			authority = true;
		} else if (num == MyuserDAO.USERLOGIN) {
			System.out.println(id + " 님 반갑습니다.");
			authority = false;
		}
	}

	private static int selectMenu(int num) {
		// while문으로 메뉴구현
		if (isAdmin()) {
			switch (num) {
			case 1:
				MyuserDAO myuserDAO = MyuserDAO.getInstance();
				myuserDAO.list();
				break;
			case 2:
				MovieDAO movieDAO = MovieDAO.getInstance();
				movieDAO.list();
				break;
			case 3:
				PayMentStaticsDAO mentStaticsDAO = PayMentStaticsDAO.getInstance();
				mentStaticsDAO.list();
				break;
			case 4:
				AdDAO adDAO = AdDAO.getInstance();
				adDAO.list();
				break;
			case 5:
				MovieAdDAO movieAdDAO = MovieAdDAO.getInstnace();
				movieAdDAO.list();
				break;
			case 6:
				EmployeeDAO employeeDAO = EmployeeDAO.getInstance();
				employeeDAO.list();
				break;
			case 7:
				EmployeeTaskDAO employeeTaskDAO = EmployeeTaskDAO.getnstance();
				employeeTaskDAO.list();
				break;
			case 8:
				ActorDAO actorDAO = ActorDAO.getInstance();
				actorDAO.list();
				break;
			case 9:
				MovieActorDAO movieActorDAO = MovieActorDAO.getInstance();
				movieActorDAO.list();
				break;
			case 10:
				RatDAO ratDAO = RatDAO.getInstance();
				ratDAO.list();
				break;
			case 11:
				PaymentDAO paymentDAO = PaymentDAO.getInstance();
				paymentDAO.list();
				break;
			case 12:
				MoviePaymentDAO moviePaymentDAO = MoviePaymentDAO.getInstance();
				moviePaymentDAO.list();
				break;
			case 13:
				SnackInfoDAO snackInfoDAO = SnackInfoDAO.getInstance();
				snackInfoDAO.list();
				break;
			case 14:
				MyuserSnackOrderDAO myuserSnackDAO = MyuserSnackOrderDAO.getInstance();
				myuserSnackDAO.list();
				break;
			case 15:
				login();
				return 0;
			case 16:
				InitDatabaseDAO initDAO = InitDatabaseDAO.getInstance();
				initDAO.list();
				break;
			case 17:
				System.out.println("프로그램이 종료됩니다 안녕히 가십시오!");
				return 10;
			case 18:
				EmployeeDAO employeeDAOver2=EmployeeDAO.getInstance();
				// 메뉴를 보여주는 함수 호출하기
				employeeDAOver2.employeeSearchMenu();
			}
		} else {
			switch (num) {
			case 1:
				MyuserDAO dao = MyuserDAO.getInstance();
				dao.selectMenu();
				break;
			case 2:
				MovieDAO movieDAO = MovieDAO.getInstance();
				movieDAO.list();
				break;
			case 3:
				RatDAO ratDAO = RatDAO.getInstance();
				ratDAO.listMe(id);
				break;
			case 4:
				PaymentDAO paymentDAO = PaymentDAO.getInstance();
				paymentDAO.list();
				break;
			case 5:
				MoviePaymentDAO moviepaymentDAO = MoviePaymentDAO.getInstance();
				moviepaymentDAO.listMe(id);
				break;
			case 6:
				SnackInfoDAO infoDAO = SnackInfoDAO.getInstance();
				infoDAO.list();
				break;
			case 7:
				MyuserSnackOrderDAO myuserSnackOrderDAO = MyuserSnackOrderDAO.getInstance();
				myuserSnackOrderDAO.listMe(id);
				break;
			case 8:
				login();
				return 0;
			case 9:
				System.out.println("프로그램이 종료됩니다 안녕히 가십시오!");
				return 10;
			}
		}
		return 0;
	}

	// 메뉴를 보여준다.
	private static void showMenu() {
		System.out.println("원하는 정보를 보여드립니다.!");
		if (isAdmin()) {
			System.out.println("1. 유저 정보");
			System.out.println("2. 영화 정보");
			System.out.println("3. 결제 통계 정보");
			System.out.println("4. 광고 정보");
			System.out.println("5. 영화 광고 정보");
			System.out.println("6. 직원 정보");
			System.out.println("7. 직원 업무 내역");
			System.out.println("8. 배우 정보");
			System.out.println("9. 영화에 출연한 배우 정보");
			System.out.println("10. 회원평점정보");
			System.out.println("11. 결제 정보");
			System.out.println("12. 영화 결제 정보");
			System.out.println("13. 간식 종류");
			System.out.println("14. 회원들이 주문한 간식 종류 ");
			System.out.println("15. 로그인 변경");
			System.out.println("16. 데이터 초기화");
			System.out.println("17. 프로그램 종료");
			
			// test > 최종때 수정요망 //
			
			System.out.println("18. 직원관리");
		} else {
			System.out.println("1. 나의 정보");
			System.out.println("2. 영화 정보");
			System.out.println("3. 나의 평점 정보");
			System.out.println("4. 결제 종류");
			System.out.println("5. 나의 결제 내역");
			System.out.println("6. 스낵정보");
			System.out.println("7. 나의 스낵주문 내역");
			System.out.println("8. 로그인 변경");
			System.out.println("9. 프로그램 종료");
		}
	}

	// 현재 세션이 admin 여부 판단.
	private static boolean isAdmin() {
		if (authority) {
			return true;
		} else {
			return false;
		}
	}
}
