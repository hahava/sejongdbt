package dao.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import com.sun.org.apache.xpath.internal.operations.Mod;

import dto.user.MoviePaymentDTO;
import dto.user.PaymentDTO;
import main.ExecuteProject;
import oracle.connect.OracleJDBCManager;

public class PaymentDAO implements DAO {

	private static PaymentDAO instance = new PaymentDAO();

	private PaymentDAO() {

	}

	public static PaymentDAO getInstance() {
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

		ArrayList<PaymentDTO> arrayList = new ArrayList<>();

		Connection conn = getConnection();
		PreparedStatement pstm = null;
		ResultSet result = null;
		String query = "select * from PAYMENT";

		try {
			pstm = conn.prepareStatement(query);
			result = pstm.executeQuery();
			while (result.next()) {
				arrayList.add(new PaymentDTO(result.getString("PAYMENT_CODE"), result.getString("PAYMENT_WAY")));
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

	public void paymentMenu() {
		int menu;
		Scanner sc = new Scanner(System.in);
		System.out.println("예약 관련 메뉴 입니다.");
		System.out.println("1. 영화 예약하기");
		System.out.println("2. 예약 수정하기");
		System.out.println("3. 예약 취소하기");
		System.out.println("4. 뒤로");
		menu = sc.nextInt();

		switch (menu) {
		case 1:
			instance.addPaymet();
			break;
		}
	}

	private void addPaymet() {
		MoviePaymentDTO dto = new MoviePaymentDTO();
		MovieDAO dao = MovieDAO.getInstance();
		PaymentDAO dao2 = PaymentDAO.getInstance();
		Scanner scanner = new Scanner(System.in);
		System.out.println("영화 예매를 진행합니다.");
		System.out.println("현재 상영중인 영화");
		instance.list();
		System.out.print("예매할  예약 코드를 입력하세요: ");
		dto.setMOVIE_PAYMENT_CODE(scanner.nextInt());
		System.out.println("예매할 영화의 코드를 입력하세요");
		dto.setMOVIE_CODE(scanner.nextLine());
	}
	
	
}
