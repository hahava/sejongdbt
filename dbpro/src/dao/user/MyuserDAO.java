package dao.user;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;
import dto.user.MyuserDTO;
import main.ExecuteProject;
import oracle.connect.OracleJDBCManager;

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

	// connection 변수
	private Connection getConnection() {
		OracleJDBCManager manager = new OracleJDBCManager();
		String oracleId = "s15010924";
		String passwd = "s15010924";
		int port = 1521;
		manager.registerOracleJDBCDriver();
		Connection conn = manager.connect(oracleId, passwd, port);
		return conn;
	}

	// myuser테이블 전부 출력
	public void list() {

		ArrayList<MyuserDTO> arrayList = new ArrayList<>();

		Connection conn = getConnection();
		PreparedStatement pstm = null;
		ResultSet result = null;
		String query = "select MYUSER_ID, MYUSER_NAME, MYUSER_PW, MYUSER_BIRTH, MYUSER_PHONE,  MYUSER_EMAIL from MYUSER";

		try {
			pstm = conn.prepareStatement(query);
			result = pstm.executeQuery();
			while (result.next()) {
				arrayList.add(new MyuserDTO(result.getString("MYUSER_ID"), result.getString("MYUSER_NAME"),
						result.getString("MYUSER_PW"), result.getDate("MYUSER_BIRTH"), result.getString("MYUSER_PHONE"),
						result.getString("MYUSER_EMAIL")));
			}
		} catch (SQLException e1) {
			System.out.println(e1);
		}

		try {
			result.close();
			pstm.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// arraylist안에 테이블내용을 전부 출력한다.
		for (int i = 0; i < arrayList.size(); i++) {
			System.out.println(arrayList.get(i).toString());
		}
	}

	// 로그인 한 유저의 정보만을 출력한다.
	public void listMe(String id) {

		ArrayList<MyuserDTO> arrayList = new ArrayList<>();

		Connection conn = getConnection();
		PreparedStatement pstm = null;
		ResultSet result = null;
		String query = "select MYUSER_ID, MYUSER_NAME, MYUSER_PW, MYUSER_BIRTH, MYUSER_PHONE,  MYUSER_EMAIL from MYUSER where MYUSER_ID = ?";

		try {
			pstm = conn.prepareStatement(query);
			pstm.setString(1, ExecuteProject.id);
			result = pstm.executeQuery();
			while (result.next()) {
				arrayList.add(new MyuserDTO(result.getString("MYUSER_ID"), result.getString("MYUSER_NAME"),
						result.getString("MYUSER_PW"), result.getDate("MYUSER_BIRTH"), result.getString("MYUSER_PHONE"),
						result.getString("MYUSER_EMAIL")));
			}
		} catch (SQLException e1) {
			System.out.println(e1);
		}

		try {
			result.close();
			pstm.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < arrayList.size(); i++) {
			System.out.println(arrayList.get(i).toString());
		}
	}

	// 로그인 메서드 id와 password를 입력받아 myuser테이블안에 저장되어 있는지 확인한다.
	public int login(String id, String pw) {
		int returnnum = NOLOGIN;
		ArrayList<MyuserDTO> arrayList = new ArrayList<>();
		Connection conn = getConnection();
		PreparedStatement pstm = null;
		ResultSet result = null;

		String query = "select * from MYUSER";

		try {
			pstm = conn.prepareStatement(query);
			result = pstm.executeQuery();
			while (result.next()) {
				arrayList.add(new MyuserDTO(result.getString("MYUSER_ID"), result.getString("MYUSER_NAME"),
						result.getString("MYUSER_PW"), result.getDate("MYUSER_BIRTH"), result.getString("MYUSER_PHONE"),
						result.getString("MYUSER_EMAIL")));
			}
		} catch (SQLException e1) {
			System.out.println(e1);
		}

		try {
			result.close();
			pstm.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (int i = 0; i < arrayList.size(); i++) {
			if (arrayList.get(i).MYUSER_ID.equals(id) && arrayList.get(i).MYUSER_PW.equals(pw) && id.equals("admin")) {
				returnnum = ADMINLOGIN;
				break;
			} else if (arrayList.get(i).MYUSER_ID.equals(id) && arrayList.get(i).MYUSER_PW.equals(pw)) {
				returnnum = USERLOGIN;
				break;
			}

		}
		return returnnum;
	}

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
			moviedao.pickBestMovieOne();
			break;
		case 2:
			moviedao.pickBestMoviePeople();

			break;
		case 3:
			myuserSnackOrderDao.pickBestSnackOne();
			return;
		case 4:
			myuserSnackOrderDao.pickBestSnackPeople();
		default:
			System.out.println("잘못 입력하셨습니다.");
			break;

		}

	}

}
