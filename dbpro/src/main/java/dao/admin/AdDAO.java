package dao.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.admin.AdDTO;
import oracle.connect.OracleJDBCManager;

public class AdDAO implements DAO {

	private AdDAO() {
	}

	// 인스턴스 생성
	private static AdDAO instance = new AdDAO();

	// 객체를 반환한다.
	public static AdDAO getInstance() {
		return instance;
	}

	// driver를 이용한 서버접속
	private Connection getConnection() {
		OracleJDBCManager manager = new OracleJDBCManager();
		String oracleId = "s15010924";
		String passwd = "s15010924";
		int port = 1521;
		manager.registerOracleJDBCDriver();
		Connection conn = manager.connect(oracleId, passwd, port);
		return conn;
	}

	// 광고테이블에 모든 내역을 출력한다.
	@Override
	public void list() {
		// TODO Auto-generated method stub

		ArrayList<AdDTO> arrayList = new ArrayList<>();

		Connection conn = getConnection();
		PreparedStatement pstm = null;
		ResultSet result = null;

		String query = "select AD_TITLE, AD_COMPANY,AD_DATE, AD_PRICE from AD";

		try {
			pstm = conn.prepareStatement(query);
			result = pstm.executeQuery();
			while (result.next()) {
				arrayList.add(new AdDTO(result.getString("AD_TITLE"), result.getString("AD_COMPANY"), result.getDate("AD_DATE"),
						result.getInt("AD_PRICE")));
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
		
		// 전부 출력한다.
		for (int i = 0; i < arrayList.size(); i++) {
			System.out.println(arrayList.get(i).toString());
		}
	}

}
