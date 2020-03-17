package dao.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.admin.MovieActorDTO;
import oracle.connect.OracleJDBCManager;

public class MovieActorDAO implements DAO {
	private Connection getConnection() {
		OracleJDBCManager manager = new OracleJDBCManager();
		String oracleId = "s15010924";
		String passwd = "s15010924";
		int port = 1521;

		Connection conn = manager.connect(oracleId, passwd, port);
		return conn;
	}

	private MovieActorDAO() {

	}

	// 객체 생성한다.
	private static MovieActorDAO instance = new MovieActorDAO();

	// 객체를 변환한다.
	public static MovieActorDAO getInstance() {
		return instance;
	}

	public void list() {
		ArrayList<MovieActorDTO> arrayList = new ArrayList<>();

		Connection conn = getConnection();
		PreparedStatement pstm = null;
		ResultSet result = null;
		String query = "select ACTOR_CODE, MOVIE_CODE, ACTOR_ROLE from MOVIE_ACTOR";

		try {
			pstm = conn.prepareStatement(query);
			result = pstm.executeQuery();
			while (result.next()) {
				arrayList.add(new MovieActorDTO(result.getString("ACTOR_CODE"), result.getString("MOVIE_CODE"), result.getString("ACTOR_ROLE")));
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
