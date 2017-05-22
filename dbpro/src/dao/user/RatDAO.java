package dao.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.user.RatDTO;
import main.ExecuteProject;
import oracle.connect.OracleJDBCManager;

public class RatDAO implements DAO {

	private static RatDAO instance = new RatDAO();

	private RatDAO() {

	}

	public static RatDAO getInstance() {
		return instance;
	}

	private Connection getConnection() {
		OracleJDBCManager manager = new OracleJDBCManager();
		String oracleId = "s15010924";
		String passwd = "s15010924";
		int port = 1521;
		Connection conn;
		manager.registerOracleJDBCDriver();
		conn = manager.connect(oracleId, passwd, port);

		return conn;
	}

	public void list() {

		ArrayList<RatDTO> arrayList = new ArrayList<>();
		Connection conn = getConnection();
		PreparedStatement pstm = null;
		ResultSet result = null;
		String query = "select * from RAT";

		try {
			pstm = conn.prepareStatement(query);
			result = pstm.executeQuery();
			while (result.next()) {
				arrayList.add(new RatDTO(result.getString("MYUSER_ID"), result.getString("MOVIE_CODE"), result.getInt("RAT_POINT"),
						result.getString("RAT_COMMENT")));
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

		ArrayList<RatDTO> arrayList = new ArrayList<>();
		Connection conn = getConnection();
		PreparedStatement pstm = null;
		ResultSet result = null;
		String query = "select * from RAT where MYUSER_ID=?";

		try {
			pstm = conn.prepareStatement(query);
			pstm.setString(1, ExecuteProject.id);
			result = pstm.executeQuery();
			while (result.next()) {
				arrayList.add(new RatDTO(result.getString("MYUSER_ID"), result.getString("MOVIE_CODE"), result.getInt("RAT_POINT"),
						result.getString("RAT_COMMENT")));
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

}
