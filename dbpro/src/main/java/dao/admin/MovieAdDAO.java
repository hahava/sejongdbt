package dao.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.admin.MovieAdDTO;
import oracle.connect.JDBCManager;

public class MovieAdDAO implements DAO {

	private MovieAdDAO() {
	}

	// 객체를 생성한다.
	private static MovieAdDAO instance = new MovieAdDAO();

	// 객체를 리턴한다.
	public static MovieAdDAO getInstnace() {
		return instance;
	}

	// 드라이버 로드한다.
	private Connection getConnection() {
		JDBCManager manager = new JDBCManager();
		String oracleId = "s15010924";
		String passwd = "s15010924";
		int port = 1521;

		Connection conn = manager.connect(oracleId, passwd, port);
		return conn;
	}

	@Override
	public void list() {

		ArrayList<MovieAdDTO> arrayList = new ArrayList<>();

		Connection conn = getConnection();
		PreparedStatement pstm = null;
		ResultSet result = null;
		String query = "select CNT, MOVIE_CODE, AD_TITLE  from MOVIE_AD";

		try {
			pstm = conn.prepareStatement(query);
			result = pstm.executeQuery();
			while (result.next()) {
				arrayList.add(new MovieAdDTO(result.getInt("CNT"), result.getString("MOVIE_CODE"), result.getString("AD_TITLE")));
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

		// 결과를 전부 출력한다.
		for (int i = 0; i < arrayList.size(); i++) {
			System.out.println(arrayList.get(i).toString());
		}
	}

}
