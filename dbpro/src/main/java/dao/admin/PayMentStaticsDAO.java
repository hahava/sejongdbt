package dao.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.admin.PaymentStaticsDTO;
import oracle.connect.OracleJDBCManager;

public class PayMentStaticsDAO implements DAO {

	// 인스턴스
	private static PayMentStaticsDAO instance = new PayMentStaticsDAO();

	// 객체 생성을 못하게 막는다.
	private PayMentStaticsDAO() {
	}

	// 인스턴스 리턴
	public static PayMentStaticsDAO getInstance() {
		return instance;
	}

	// 오라클 드라이버를 로드한다.
	private Connection getConnection() {
		OracleJDBCManager manager = new OracleJDBCManager();
		String oracleId = "s15010924";
		String passwd = "s15010924";
		int port = 1521;
		manager.registerOracleJDBCDriver();
		Connection conn = manager.connect(oracleId, passwd, port);
		return conn;
	}

	// 결제내역을 전부 출력한다.
	@Override
	public void list() {

		ArrayList<PaymentStaticsDTO> arrayList = new ArrayList<>();

		Connection conn = getConnection();
		PreparedStatement pstm = null;
		ResultSet result = null;

		String query = "select PAYMENT_DATE, PAYMENT_SUM, PAYMENT_COUNT, MYUSER_COUNT from Payment_statics";

		try {
			pstm = conn.prepareStatement(query);
			result = pstm.executeQuery();
			while (result.next()) {
				arrayList.add(new PaymentStaticsDTO(result.getDate("PAYMENT_DATE"), result.getInt("PAYMENT_SUM"), result.getInt("PAYMENT_COUNT"),
						result.getInt("MYUSER_COUNT")));
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

}
