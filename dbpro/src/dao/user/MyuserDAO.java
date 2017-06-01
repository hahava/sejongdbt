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
	public final static int NOLOGIN = 0;
	public final static int ADMINLOGIN = 1;
	public final static int USERLOGIN = 2;
	public final static int NEW = 3;

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
		String query = "select * from MYUSER";

		try {
			pstm = conn.prepareStatement(query);
			result = pstm.executeQuery();
			while (result.next()) {
				arrayList.add(new MyuserDTO(result.getString("MYUSER_ID"), result.getString("MYUSER_NAME"), result.getString("MYUSER_PW"),
						result.getDate("MYUSER_BIRTH"), result.getString("MYUSER_PHONE"), result.getString("MYUSER_EMAIL")));
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
			System.out.println(arrayList.get(i).toString());
		}
	}

	public void listMe(String id) {

		ArrayList<MyuserDTO> arrayList = new ArrayList<>();

		Connection conn = getConnection();
		PreparedStatement pstm = null;
		ResultSet result = null;
		String query = "select * from MYUSER where MYUSER_ID = ?";

		try {
			pstm = conn.prepareStatement(query);
			pstm.setString(1, ExecuteProject.id);
			result = pstm.executeQuery();
			while (result.next()) {
				arrayList.add(new MyuserDTO(result.getString("MYUSER_ID"), result.getString("MYUSER_NAME"), result.getString("MYUSER_PW"),
						result.getDate("MYUSER_BIRTH"), result.getString("MYUSER_PHONE"), result.getString("MYUSER_EMAIL")));
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
			System.out.println(arrayList.get(i).toString());
		}
	}

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
				arrayList.add(new MyuserDTO(result.getString("MYUSER_ID"), result.getString("MYUSER_NAME"), result.getString("MYUSER_PW"),
						result.getDate("MYUSER_BIRTH"), result.getString("MYUSER_PHONE"), result.getString("MYUSER_EMAIL")));
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

	private void modify(String id) {
		Connection conn = getConnection();
		PreparedStatement pstm = null;
		ResultSet result = null;
		String email;
		String phone;
		Scanner scanner = new Scanner(System.in);
		System.out.println("이메일과 번호를 입력하세요");

		System.out.printf("email: ");
		email = scanner.nextLine();
		System.out.printf("phone: ");
		phone = scanner.nextLine();

		String query = "update myuser set myuser_email = ?,  myuser_phone = ? where myuser_id = ?";
		try {
			conn.setAutoCommit(false);
			pstm = conn.prepareStatement(query);
			pstm.setString(1, email);
			pstm.setString(2, phone);
			pstm.setString(3, id);
			int cnt = pstm.executeUpdate();
			conn.commit();
			conn.setAutoCommit(true);
		} catch (SQLException e1) {
			System.out.println(e1);
			System.out.println("fail...");
		}
		try {
			// result.close();
			pstm.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void menu() {
		System.out.println("1. 나의 정보 보기");
		System.out.println("2. 내 정보 수정하기");
		System.out.println("3. 이전메뉴로");
	}

	public void selectMenu() {
		int num = -1;
		Scanner scanner = new Scanner(System.in);
		while (num != 0) {
			menu();
			num = scanner.nextInt();
			switch (num) {
			case 1:
				instance.listMe(ExecuteProject.id);
				break;
			case 2:
				instance.modify(ExecuteProject.id);
				break;
			case 3:
				return;
			}
		}
	}

	public void insertMyuser() {
		MyuserDTO dto = new MyuserDTO();
		Scanner scanner = new Scanner(System.in);
		System.out.println("회원 가입을 진행합니다.");
		while (true) {
			System.out.print("id: ");
			dto.MYUSER_ID = scanner.nextLine();
			if (!chechId(dto.MYUSER_ID)) {
				System.out.println("중복입니다. 다른 아이디를 입력하세요!");
			}
			System.out.print("이름: ");
			dto.MYUSER_NAME = scanner.nextLine();
			System.out.print("pass: ");
			dto.MYUSER_PW = scanner.nextLine();
			System.out.printf("생년월일(yyyymmdd): ");
			try {
				String temp = scanner.nextLine();
				Date date = new Date(Long.parseLong(temp));
				dto.MYUSER_BIRTH = date;
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			System.out.print("phone: ");
			dto.MYUSER_PHONE = scanner.nextLine();
			System.out.print("email: ");
			dto.MYUSER_EMAIL = scanner.nextLine();

			break;
		}

		Connection conn = getConnection();
		PreparedStatement pstm = null;
		// ResultSet result = null;
		String query = "insert into myuser values(?,?,?,?,?,?)";

		try {
			conn.setAutoCommit(false);
			pstm = conn.prepareStatement(query);
			pstm.setString(1, dto.getMYUSER_ID());
			pstm.setString(2, dto.getMYUSER_NAME());
			pstm.setString(3, dto.getMYUSER_PW());
			pstm.setDate(4, dto.getMYUSER_BIRTH());
			pstm.setString(5, dto.getMYUSER_PHONE());
			pstm.setString(6, dto.getMYUSER_EMAIL());

			int cnt = pstm.executeUpdate();
			conn.commit();
			conn.setAutoCommit(true);
		} catch (SQLException e1) {
			System.out.println(e1);
		}

		try {
			// result.close();
			pstm.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("회원가입 성공! 로그인해주세요!");
		System.out.println("\n\n\n\n\n\n\n");
	}

	// 회원 중복을 체크하는 메서드
	private boolean chechId(String id) {
		boolean check = true;
		Connection conn = getConnection();
		PreparedStatement pstm = null;
		ResultSet result = null;
		String query = "select myuser_id from MYUSER";

		try {
			pstm = conn.prepareStatement(query);
			result = pstm.executeQuery();
			while (result.next()) {
				if (result.getString("myuser_id").equals(id)) {
					check = false;
					break;
				}
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
		return check;
	}

}
