package dao.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import dto.user.*;
import main.ExecuteProject;
import oracle.connect.OracleJDBCManager;

public class MoviePaymentDAO implements DAO {

	private static MoviePaymentDAO instance = new MoviePaymentDAO();

	private MoviePaymentDAO() {
	}

	public static MoviePaymentDAO getInstance() {
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

		ArrayList<MoviePaymentDTO> arrayList = new ArrayList<>();

		Connection conn = getConnection();
		PreparedStatement pstm = null;
		ResultSet result = null;
		String query = "select * from ?";

		try {
			pstm = conn.prepareStatement(query);
			pstm.setString(1, "movie_payment");
			result = pstm.executeQuery();
			while (result.next()) {
				arrayList.add(new MoviePaymentDTO(result.getInt("MOVIE_PAYMENT_CODE"), result.getString("MYUSER_ID"), result.getString("MOVIE_CODE"),
						result.getString("PAYMENT_CODE"), result.getDate("PAYMENT_DATE")));
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

		ArrayList<MoviePaymentDTO> arrayList = new ArrayList<>();

		Connection conn = getConnection();
		PreparedStatement pstm = null;
		ResultSet result = null;
		String query = "select * from MOVIE_PAYMENT where MYUSER_ID=?";

		try {
			pstm = conn.prepareStatement(query);
			pstm.setString(1, ExecuteProject.id);
			result = pstm.executeQuery();
			while (result.next()) {
				arrayList.add(new MoviePaymentDTO(result.getInt("MOVIE_PAYMENT_CODE"), result.getString("MYUSER_ID"), result.getString("MOVIE_CODE"),
						result.getString("PAYMENT_CODE"), result.getDate("PAYMENT_DATE")));
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
