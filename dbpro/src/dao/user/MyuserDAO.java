package dao.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import com.sun.xml.internal.stream.Entity.ScannedEntity;

import dto.user.MyuserDTO;
import main.ExecuteProject;
import oracle.connect.OracleJDBCManager;

public class MyuserDAO implements DAO {

	public final static int ADMINLOGIN = 1;
	public final static int USERLOGIN = 2;
	public final static int NOLOGIN = 0;

	private static MyuserDAO instance = new MyuserDAO();

	private MyuserDAO() {
	}

	public static MyuserDAO getInstance() {
		return instance;
	}

	private Connection getConnection() {
		OracleJDBCManager manager = new OracleJDBCManager();
		String oracleId = "s15010924";
		String passwd = "s15010924";
		int port = 1521;
		manager.registerOracleJDBCDriver();
		Connection conn = manager.connect(oracleId, passwd, port);
		return conn;
	}

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

		String query = "update myuser set myuser_email = ?,  myuser_phone = ? where myuser_name = ?";
		try {
			conn.setAutoCommit(false);
			pstm = conn.prepareStatement(query);
			pstm.setString(1, email);
			pstm.setString(2, phone);
			pstm.setString(3, id);
			pstm.executeUpdate();
			conn.commit();
		} catch (SQLException e1) {
			System.out.println(e1);
			System.out.println("fail...");
		}

		try {
			// result.close();
			pstm.close();
			conn.close();
			conn.setAutoCommit(true);
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

}
