package main;

import java.io.IOException;

import auth.Account;
import dao.admin.*;
import dao.user.*;
import feat.actor.ActorDAO;
import feat.advertisement.AdDAO;
import feat.employee.EmployeeDAO;
import feat.employeetask.EmployeeTaskDAO;
import feat.movie.MovieDAO;
import feat.payment.PaymentDAO;
import feat.paymentstatics.PaymentStaticsDAO;
import feat.rat.RatDAO;
import feat.snack.SnackInfoDAO;
import menu.MainView;
import util.ConsoleUtil;

/**
 * @author kalin
 * @date 2017. 6. 8.
 * @filename
 * @description admin 메뉴의 경우 실행할 때 2번, 18~20번 메뉴를 실행하면 된다.
 */
public class ExecuteProject {

	private static final int EXIT = 0;
	public static boolean authority = false; // true인경우 관리자
	public static String id; // 로그인 세션기능

	public static void main(String[] args) throws IOException {

		while (Account.isLogin() == false) {
			String id = ConsoleUtil.readString("아이디를 입력해주세요.");
			String pw = ConsoleUtil.readString("패스워드를 입력해주세요");
			Account.doLogin(id, pw);
		}

		while (true) {
			MainView.show();
			int requestCode = ConsoleUtil.readInt("원하는 메뉴를 선택해주세요");
			if (requestCode == EXIT) {
				System.exit(0);
			}
		}
	}

	// while문으로 메뉴구현
	private static int selectMenu(int num) {
		if (isAdmin()) {
			switch (num) {
			case 1:
				MyuserDAO myuserDAO = MyuserDAO.getInstance();
				myuserDAO.selectSnacks();
				break;
			case 2:
				MovieDAO movieDAO = MovieDAO.getInstance();
//				movieDAO.movieMenu();
				break;
			case 3:
				PaymentStaticsDAO mentStaticsDAO = PaymentStaticsDAO.getInstance();
				mentStaticsDAO.selectPaymentStatics();
				break;
			case 4:
				AdDAO adDAO = AdDAO.getInstance();
//				adDAO.selectAdvertiseMents();
				break;
			case 5:
				MovieAdDAO movieAdDAO = MovieAdDAO.getInstance();
				movieAdDAO.list();
				break;
			case 6:
				EmployeeDAO employeeDAO = EmployeeDAO.getInstance();
				employeeDAO.selectEmployees();
				break;
			case 7:
				EmployeeTaskDAO employeeTaskDAO = EmployeeTaskDAO.getInstance();
				employeeTaskDAO.selectEmployeeTasks();
				break;
			case 8:
				ActorDAO actorDAO = ActorDAO.getInstance();
				actorDAO.selectActors();
				break;
			case 9:
				MovieActorDAO movieActorDAO = MovieActorDAO.getInstance();
				movieActorDAO.list();
				break;
			case 10:
				RatDAO ratDAO = RatDAO.getInstance();
				ratDAO.selectRatings();
				break;
			case 11:
				PaymentDAO paymentDAO = PaymentDAO.getInstance();
				paymentDAO.selectPaymentWays();
				break;
			case 12:
				MoviePaymentDAO moviePaymentDAO = MoviePaymentDAO.getInstance();
				moviePaymentDAO.selectSnacks();
				break;
			case 13:
				SnackInfoDAO snackInfoDAO = SnackInfoDAO.getInstance();
				snackInfoDAO.selectSnacks();
				break;
			case 14:
				MyuserSnackOrderDAO myuserSnackDAO = MyuserSnackOrderDAO.getInstance();
				myuserSnackDAO.list();
				break;
			case 15:
//				login();
				return 0;
			case 16:
				InitDatabaseDAO initDAO = InitDatabaseDAO.getInstance();
				initDAO.selectEmployees();
				break;
			case 17:
				System.out.println("프로그램이 종료됩니다 안녕히 가십시오!");
				return 10;
			case 18:
				EmployeeDAO employeeDAOver2 = EmployeeDAO.getInstance();
//				employeeDAOver2.employeeSearchMenu();
				break;
			case 19:
				MyuserDAO myuserDAOver2 = MyuserDAO.getInstance();
				myuserDAOver2.pickBestMenu();
				break;
			case 20:
				MovieDAO moviedaover3 = MovieDAO.getInstance();
				moviedaover3.movieStaticsMenu();
				break;
			default:
				System.out.println("잘못된 입력입니다.");
			}
		} else {
			switch (num) {
			case 1:
				MyuserDAO dao = MyuserDAO.getInstance();
				dao.selectUserInfo(id);
				break;
			case 2:
				MovieDAO movieDAO = MovieDAO.getInstance();
//				movieDAO.movieMenu();
				break;
			case 3:
				RatDAO ratdaover2 = RatDAO.getInstance();
//				ratdaover2.selectMovieRat();
				break;
			case 4:
				RatDAO ratDAO = RatDAO.getInstance();
				ratDAO.selectRatListByUserId(id);
				break;
			case 5:
				PaymentDAO paymentDAO = PaymentDAO.getInstance();
//				paymentDAO.paymentMenu();
				break;
			case 6:
				MoviePaymentDAO moviepaymentDAO = MoviePaymentDAO.getInstance();
				moviepaymentDAO.listMe(id);
				break;
			case 7:
				SnackInfoDAO infoDAO = SnackInfoDAO.getInstance();
				infoDAO.selectSnacks();
				break;
			case 8:
				MyuserSnackOrderDAO myuserSnackOrderDAO = MyuserSnackOrderDAO.getInstance();
				myuserSnackOrderDAO.selectSnackOrders(id);
				break;
			case 9:
//				login();
				return 0;
			case 10:
				System.out.println("프로그램이 종료됩니다 안녕히 가십시오!");
				return 10;
			default:
				System.out.println("잘못된 입력입니다.");
			}
		}
		return 0;
	}

	// 현재 세션의 admin 여부 판단.
	private static boolean isAdmin() {
		if (authority) {
			return true;
		} else {
			return false;
		}
	}
}
