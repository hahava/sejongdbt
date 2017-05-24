package dao.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.admin.ActorDTO;
import oracle.connect.*;

public class ActorDAO implements DAO {

	private Connection getConnection() {
		OracleJDBCManager manager = new OracleJDBCManager();
		String oracleId = "s15010924";
		String passwd = "s15010924";
		int port = 1521;
		manager.registerOracleJDBCDriver();
		Connection conn = manager.connect(oracleId, passwd, port);
		return conn;
	}

	private ActorDAO() {

	}

	private static ActorDAO instance = new ActorDAO();

	public static ActorDAO getInstance() {
		return instance;
	}

	public void list() {

		ArrayList<ActorDTO> actor = new ArrayList<>();

		Connection conn = getConnection();
		PreparedStatement pstm = null;
		ResultSet result = null;
		String query = "select * from ACTOR";

		try {
			pstm = conn.prepareStatement(query);
			result = pstm.executeQuery();
			while (result.next()) {
				actor.add(new ActorDTO(result.getString("actor_code"), result.getString("actor_name"),
						result.getString("actor_gender"), result.getString("actor_birth")));
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
		for (int i = 0; i < actor.size(); i++) {
			System.out.println(actor.get(i).toString());
		}
	}
}
