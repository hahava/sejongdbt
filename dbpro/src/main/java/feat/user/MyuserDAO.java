package feat.user;

import dao.user.DAO;
import dao.user.MyuserSnackOrderDAO;
import feat.movie.MovieDAO;
import oracle.connect.JDBCManager;
import org.apache.commons.lang3.StringUtils;

import java.sql.Connection;
import java.util.Map;
import java.util.Scanner;

public class MyuserDAO implements DAO {

	// 로그인 상태
	public final static int NOLOGIN = 0; // 로그인 실패
	public final static int ADMINLOGIN = 1; // admin으로 로그인이 됨
	public final static int USERLOGIN = 2; // user로 로그인이 됨

	private static MyuserDAO instance = new MyuserDAO();

	// 캡슐화
	private MyuserDAO() {
	}

	// 만들어진 인스턴스 리턴
	public static MyuserDAO getInstance() {
		return instance;
	}

	// connection 메서드
	private Connection getConnection() {
		JDBCManager manager = new JDBCManager();
		String oracleId = "s15010924";
		String passwd = "s15010924";
		int port = 1521;

		Connection conn = manager.connect(oracleId, passwd, port);
		return conn;
	}

	// myuser테이블 전부 출력
	public void selectSnacks() {
		final String query = "SELECT " +
			"	MYUSER_ID, " +
			"	MYUSER_NAME, " +
			"	MYUSER_PW, " +
			"	MYUSER_BIRTH, " +
			"	MYUSER_PHONE, " +
			"	MYUSER_EMAIL " +
			"FROM " +
			"	MYUSER";

		JDBCManager
			.getInstance()
			.queryForList(query, MyuserDTO.class)
			.forEach(myuserDTO -> System.out.println(myuserDTO.toString()));

	}

	// 로그인 한 유저의 정보만을 출력한다.
	public void selectUserInfo(String id) {

		final String query = "SELECT " +
			"	MYUSER_ID, " +
			"	MYUSER_NAME, " +
			"	MYUSER_PW, " +
			"	MYUSER_BIRTH, " +
			"	MYUSER_PHONE, " +
			"	MYUSER_EMAIL " +
			"FROM " +
			"	MYUSER " +
			"WHERE " +
			"	MYUSER_ID = "+ StringUtils.wrap(id, "'");

		JDBCManager
			.getInstance()
			.queryForList(query, MyuserDTO.class)
			.forEach(myuserDTO -> System.out.println(myuserDTO.toString()));
	}

	// 로그인 메서드 id와 password를 입력받아 myuser테이블안에 저장되어 있는지 확인한다. 로그인 가능 여부를 확인 하는 메서드
	public Map<String, Object> login(String id, String pw) {
		final String query = "SELECT " +
			"	MYUSER_ID " +
			"FROM " +
			"	MYUSER " +
			"WHERE " +
			"	MYUSER_ID = " + StringUtils.wrap(id, "'") + " " +
			"AND " +
			"	MYUSER_PW = " + StringUtils.wrap(pw, "'");

		return JDBCManager
			.getInstance()
			.queryForMap(query, new String[] {"MYUSER_ID"});

	}
	
	//우수회원을 선별 하는 메뉴
	public void pickBestMenu() {
		int menu;
		Scanner sc = new Scanner(System.in);

		System.out.println("이달의 우수회원을 선출합니다.");
		System.out.println("1. 영화왕)가장 많이 본 회원 1명 선출");
		System.out.println("2. 영화왕)범위 지정 후 여러명 선출");
		System.out.println("3. 스낵왕)스낵에 돈을 가장 많이 쓴 회원 선출");
		System.out.println("4. 스낵왕)범위 지정후 여러명 선출");
		
		menu = sc.nextInt();
		
		MovieDAO moviedao = MovieDAO.getInstance();
		MyuserSnackOrderDAO myuserSnackOrderDao = MyuserSnackOrderDAO.getInstance();

		switch (menu) {
		case 1:
			moviedao.getPersonWhoBookedTheMostMovies();
			break;
		case 2:
//			moviedao.getMovieReservationCountOfPerson();

			break;
		case 3:
			myuserSnackOrderDao.selectMostOrderedMember();
			return;
		case 4:
//			myuserSnackOrderDao.selectMinPriceOrders();
		default:
			System.out.println("잘못 입력하셨습니다.");
			break;

		}

	}

}
