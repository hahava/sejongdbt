package main;

import java.util.Scanner;

import dao.admin.*;
import dao.user.*;
import dto.user.MyuserDTO;
import oracle.connect.Connector;

public class ExecuteProject {

	public static boolean authority = false;
	public static String id;

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		int num = 0;

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
		MyuserDAO dao = new MyuserDAO();
		while (num == 0) {
			System.out.println("아이디와 패스워드를 입력해주세요");
			id = scanner.nextLine();
			pw = scanner.nextLine();
			num = dao.login(id, pw);
			if (num == MyuserDAO.NOLOGIN) {
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
		// TODO Auto-generated method stub
		if (isAdmin()) {
			switch (num) {
			case 1:
				MyuserDAO myuserDAO = new MyuserDAO();
				myuserDAO.list();
				break;
			case 2:
				MovieDAO movieDAO = new MovieDAO();
				movieDAO.list();
				break;
			case 3:
				PayMentStaticsDAO mentStaticsDAO = new PayMentStaticsDAO();
				mentStaticsDAO.list();
				break;
			case 4:
				AdDAO adDAO = new AdDAO();
				adDAO.list();
				break;
			case 5:
				MovieAdDAO movieAdDAO = new MovieAdDAO();
				movieAdDAO.list();
				break;
			case 6:
				EmployeeDAO employeeDAO = new EmployeeDAO();
				employeeDAO.list();
				break;
			case 7:
				EmployeeTaskDAO employeeTaskDAO = new EmployeeTaskDAO();
				employeeTaskDAO.list();
				break;
			case 8:
				login();
				return 0;
			case 9:
				// insert init function//
				InitDatabaseDAO initDAO = new InitDatabaseDAO();
				initDAO.list();
				break;
			// finished //

			case 10:
				System.out.println("프로그램이 종료됩니다 안녕히 가십시오!");
				return 10;
			}
		} else {
			switch (num) {
			case 1:
				MyuserDAO dao = new MyuserDAO();
				dao.listMe(id);
				break;
			case 2:
				MovieDAO movieDAO = new MovieDAO();
				movieDAO.list();
				break;
			case 3:
				RatDAO ratDAO = new RatDAO();
				ratDAO.list();
				break;
			case 4:
				PaymentDAO paymentDAO = new PaymentDAO();
				paymentDAO.list();
				break;
			case 5:
				//need to adding
				break;
			case 6:
				SnackInfoDAO infoDAO = new SnackInfoDAO();
				infoDAO.list();
				break;
			case 7:
				MyuserSnackOrderDAO myuserSnackOrderDAO = new MyuserSnackOrderDAO();
				myuserSnackOrderDAO.list();
				break;
			case 8:
				login();
				return 0;
			case 9:
				return 10;
			}
		}
		return 0;
	}

	private static void showMenu() {

		System.out.println("원하는 정보를 보여드립니다.!");
		if (isAdmin()) {
			System.out.println("1. 유저 정보");
			System.out.println("2. 영화 정보");
			System.out.println("3. 평점 통계 정보");
			System.out.println("4. 광고 정보");
			System.out.println("5. 광고 삽입");
			System.out.println("6. 직원 정보");
			System.out.println("7. 직원 업무 내역");
			System.out.println("8. 로그인 변경");
			System.out.println("9. 데이터 초기화");
			System.out.println("10. 프로그램 종료");
		} else {
			System.out.println("1. 나의 정보");
			System.out.println("2. 영화 정보");
			System.out.println("3. 평점 정보");
			System.out.println("4. 가능한 결제 종류");
			System.out.println("5. 결제 내역");
			System.out.println("6. 스낵정보");
			System.out.println("7. 스낵주문 내역");
			System.out.println("8. 로그인 변경");
			System.out.println("9. 프로그램 종료");
		}
	}

	private static boolean isAdmin() {
		if (authority) {
			return true;
		} else {
			return false;
		}
	}
}
