package dao.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.user.SnackInfoDTO;
import oracle.connect.OracleJDBCManager;

public class SnackInfoDAO implements DAO {
	private Connection getConnection() {
		OracleJDBCManager manager = new OracleJDBCManager();
		String oracleId = "s15010924";
		String passwd = "s15010924";
		int port = 1521;
		manager.registerOracleJDBCDriver();
		Connection conn = manager.connect(oracleId, passwd, port);
		return conn;
	}

	private SnackInfoDAO() {
	}

	private static SnackInfoDAO instance = new SnackInfoDAO();

	public static SnackInfoDAO getInstance() {
		return instance;
	}

	@Override
	public void list() {
		ArrayList<SnackInfoDTO> arrayList = new ArrayList<>();

		Connection conn = getConnection();
		PreparedStatement pstm = null;
		ResultSet result = null;
		String query = "select * from SNACK_INFO";

		try {
			pstm = conn.prepareStatement(query);
			result = pstm.executeQuery();
			while (result.next()) {
				arrayList.add(new SnackInfoDTO(result.getString("SNACK_CODE"), result.getString("SNACK_NAME"),
						result.getString("SNACK_CONTENT"), result.getInt("SNACK_CAL"), result.getInt("SNACK_PRICE")));
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
