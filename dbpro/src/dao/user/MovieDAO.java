package dao.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import dto.user.*;
import oracle.connect.OracleJDBCManager;

public class MovieDAO implements DAO {
	public void list() {

		OracleJDBCManager manager = new OracleJDBCManager();
		String oracleId = "s15010924";
		String passwd = "s15010924";
		int port = 1521;
		manager.registerOracleJDBCDriver();

		ArrayList<MovieDTO> movie = new ArrayList<>();

		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet result = null;
		String query = "select * from movie";

		conn = manager.connect(oracleId, passwd, port);
		try {
			pstm = conn.prepareStatement(query);
			result = pstm.executeQuery();
			while (result.next()) {
				movie.add(new MovieDTO(result.getString("MOVIE_CODE"), result.getString("MOVIE_TITLE"),
						result.getString("MOVIE_DIRECTOR"), result.getInt("MOVIE_AGE"), result.getString("MOVIE_GENRE"),
						result.getDate("MOVIE_START"), result.getDate("MOVIE_END")));
			}

		} catch (SQLException e1) {
			System.out.println(e1);
			System.out.println(e1.getMessage());
		}

		try {
			result.close();
			pstm.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i = 0; i < movie.size(); i++) {
			System.out.println(movie.get(i).toString());
		}
	}

}
