package dao.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import dto.user.MoviePaymentDTO;
import dto.user.PaymentDTO;
import main.ExecuteProject;
import oracle.connect.JDBCManager;

public class PaymentDAO implements DAO {

	private static PaymentDAO instance = new PaymentDAO();

	private PaymentDAO() {

	}

	public static PaymentDAO getInstance() {
		return instance;
	}

	private Connection getConnection() {
		JDBCManager manager = new JDBCManager();
		String oracleId = "s15010924";
		String passwd = "s15010924";
		int port = 1521;

		Connection conn = manager.connect(oracleId, passwd, port);
		return conn;
	}

	// 결제 방법을 출력한다.
	public void list() {
		final String query = "SELECT PAYMENT_CODE, PAYMENT_WAY FROM PAYMENT";
		JDBCManager
			.getInstance()
			.queryForList(query, PaymentDTO.class)
			.forEach(paymentDTO -> System.out.println(paymentDTO.toString()));
	}

	// 유저로 로그인할 경우 영화 예약과 관련된 메뉴
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
//			instance.updatePayment();
			break;
		case 3:
			instance.deletePayment(0);
			break;
		default:
			System.out.println("잘못된 입력입니다!");

		}
	}

	// 예매 목록을 코드를 이용하여 삭제 하는 메뉴이다.
	public void deletePayment(final int movieCode) {
		MoviePaymentDAO moviePaymentDAO = MoviePaymentDAO.getInstance();
		System.out.println("나의 예매 목록");
//		moviePaymentDAO.listMe(ExecuteProject.id);
		System.out.print("취소할 예매 코드: ");

		final String query = "DELETE " +
			"FROM " +
			"	MOVIE_PAYMENT " +
			"WHERE " +
			"	MOVIE_PAYMENT_CODE = ?";

		int result = JDBCManager
			.getInstance()
			.delete(query, new Object[] {movieCode});
		System.out.println("삭제가 완료되었습니다!");
	}

	// 영화 수정을 할 수 있는 메뉴이다.
	public void updatePayment(MoviePaymentDTO moviePaymentDTO) {


		System.out.println("예매한 영화를 수정합니다.");

		System.out.println("나의 예매 목록");

		System.out.println("수정할 예매 번호를 입력하세요");

		System.out.println("어떤 영화로 바꾸시겠습니까?");
		System.out.println("바꿀 영화의 코드를 입력하세요");

		System.out.println("결제 방법을 선택하세요");
		System.out.println("결제 방법의 코드를 입력하세요");

		final String query = "UPDATE " +
			"	MOVIE_PAYMENT " +
			"SET " +
			"	MOVIE_CODE = ?, " +
			"	PAYMENT_CODE = ?, " +
			"	PAYMENT_DATE = ? " +
			"WHERE " +
			"	MOVIE_PAYMENT_CODE = ?";

		JDBCManager.getInstance().update(query,
			new Object[] {
				moviePaymentDTO.getMovieCode(),
				moviePaymentDTO.getPaymentCode(),
				moviePaymentDTO.getPaymentDate(),
				moviePaymentDTO.getMoviePaymentCode()
		});
		System.out.println("수정이 완료되었습니다!");
	}

	//예약할 수 있는 메뉴
	private void addPaymet() {

		MoviePaymentDTO dto = new MoviePaymentDTO();
		MovieDAO dao = MovieDAO.getInstance();
		PaymentDAO dao2 = PaymentDAO.getInstance();

		Scanner scanner = new Scanner(System.in);

		System.out.println("영화 예매를 진행합니다.");

		System.out.println("현재 상영중인 영화");
		dao.list();
		System.out.println("예매할 영화의 코드를 입력하세요");
		dto.setMovieCode(scanner.nextLine());

		System.out.println("예약 방식의 코드를 입력하세요");
		instance.list();
		dto.setPaymentCode(scanner.nextLine());

		dto.setMyuserId(ExecuteProject.id);
		dto.setPaymentDate(java.sql.Date.valueOf(timeNow()));

		Connection conn = getConnection();
		PreparedStatement pstm = null;
		String query = "insert into movie_payment values (movie_payment_inc.nextval,?,?,?,?)";

		try {

			pstm = conn.prepareStatement(query);
			pstm.setString(1, dto.getMyuserId());
			pstm.setString(2, dto.getMovieCode());
			pstm.setString(3, dto.getPaymentCode());
			pstm.setDate(4, dto.getPaymentDate());
			pstm.executeUpdate();

			pstm = conn.prepareStatement("commit");
			pstm.executeUpdate();

		} catch (SQLException e1) {
			System.out.println(e1);
			e1.printStackTrace();
		}

	}

	//현재 시각을 반환한다.
	private String timeNow() {
		long time = System.currentTimeMillis();
		SimpleDateFormat dayTime = new SimpleDateFormat("yyyy-MM-dd");
		String str = dayTime.format(new Date(time));
		return str;
	}

}
