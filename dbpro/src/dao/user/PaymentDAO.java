package dao.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import com.sun.org.apache.xpath.internal.operations.Mod;
import com.sun.xml.internal.ws.api.pipe.NextAction;

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
		case 2:
			instance.modifyPayment();
			break;
		case 3:
			instance.deletePayment();
			break;
		default:
			System.out.println("잘못된 입력입니다!");

		}
	}

	private void deletePayment() {
		MoviePaymentDAO moviePaymentDAO = MoviePaymentDAO.getInstance();
		System.out.println("나의 예매 목록");
		moviePaymentDAO.listMe(ExecuteProject.id);
		int movie_payment_code = 0;
		Scanner scanner = new Scanner(System.in);
		System.out.print("취소할 예매 코드: ");
		movie_payment_code = scanner.nextInt();

		Connection conn = getConnection();
		PreparedStatement pstm = null;
		String query = "delete from movie_payment where movie_payment_code = ?";

		try {
			pstm = conn.prepareStatement(query);
			pstm.setInt(1, movie_payment_code);
			pstm.executeUpdate();
			
			pstm = conn.prepareStatement("commit");
			pstm.executeUpdate();

		} catch (SQLException e1) {
			System.out.println(e1);
			e1.printStackTrace();
		}
		try {
			pstm.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("삭제가 완료되었습니다!");
	}

	private void modifyPayment() {

		MoviePaymentDTO dto = new MoviePaymentDTO();
		MovieDAO dao = MovieDAO.getInstance();
		PaymentDAO dao2 = PaymentDAO.getInstance();
		Scanner scanner = new Scanner(System.in);

		MoviePaymentDAO moviePaymentDAO = MoviePaymentDAO.getInstance();

		System.out.println("예매한 영화를 수정합니다.");

		System.out.println("나의 예매 목록");
		moviePaymentDAO.listMe(ExecuteProject.id);

		System.out.println("수정할 예매 번호를 입력하세요");
		dto.setMOVIE_PAYMENT_CODE(scanner.nextInt());

		scanner.nextLine();

		System.out.println("어떤 영화로 바꾸시겠습니까?");
		dao.list();
		System.out.println("바꿀 영화의 코드를 입력하세요");
		dto.setMOVIE_CODE(scanner.nextLine());

		System.out.println("결제 방법을 선택하세요");
		dao2.list();
		System.out.println("결제 방법의 코드를 입력하세요");
		dto.setPAYMENT_CODE(scanner.nextLine());

		dto.setMYUSER_ID(ExecuteProject.id);
		dto.setPAYMENT_DATE(java.sql.Date.valueOf(timeNow()));

		Connection conn = getConnection();
		PreparedStatement pstm = null;
		String query = "update  movie_payment set MOVIE_CODE = ? ,PAYMENT_CODE = ? ,PAYMENT_DATE = ? where movie_payment_code = ?";

		try {
			pstm = conn.prepareStatement(query);

			pstm.setString(1, dto.getMOVIE_CODE());
			pstm.setString(2, dto.getPAYMENT_CODE());
			pstm.setDate(3, dto.getPAYMENT_DATE());
			pstm.setInt(4, dto.getMOVIE_PAYMENT_CODE());
			pstm.executeUpdate();
			
			pstm = conn.prepareStatement("commit");
			pstm.executeUpdate();

		} catch (SQLException e1) {
			System.out.println(e1);
			e1.printStackTrace();
		}
		try {
			pstm.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("수정이 완료되었습니다!");
	}

	private void addPaymet() {
		MoviePaymentDTO dto = new MoviePaymentDTO();
		MovieDAO dao = MovieDAO.getInstance();
		PaymentDAO dao2 = PaymentDAO.getInstance();
		Scanner scanner = new Scanner(System.in);

		System.out.println("영화 예매를 진행합니다.");

		System.out.println("현재 상영중인 영화");
		dao.list();
		System.out.println("예매할 영화의 코드를 입력하세요");
		dto.setMOVIE_CODE(scanner.nextLine());

		System.out.println("예약 방식의 코드를 입력하세요");
		instance.list();
		dto.setPAYMENT_CODE(scanner.nextLine());

		dto.setMYUSER_ID(ExecuteProject.id);
		dto.setPAYMENT_DATE(java.sql.Date.valueOf(timeNow()));

		Connection conn = getConnection();
		PreparedStatement pstm = null;
		String query = "insert into movie_payment values (movie_payment_inc.nextval,?,?,?,?)";

		
		
		try {
			
			pstm = conn.prepareStatement(query);
			pstm.setString(1, dto.getMYUSER_ID());
			pstm.setString(2, dto.getMOVIE_CODE());
			pstm.setString(3, dto.getPAYMENT_CODE());
			pstm.setDate(4, dto.getPAYMENT_DATE());
			pstm.executeUpdate();
			
			pstm = conn.prepareStatement("commit");
			pstm.executeUpdate();
			

		} catch (SQLException e1) {
			System.out.println(e1);
			e1.printStackTrace();
		}

	}

	private String timeNow() {
		long time = System.currentTimeMillis();
		SimpleDateFormat dayTime = new SimpleDateFormat("yyyy-MM-dd");
		String str = dayTime.format(new Date(time));
		return str;
	}

}
