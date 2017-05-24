package dao.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.user.MyuserSnackOrderDTO;
import main.ExecuteProject;
import oracle.connect.OracleJDBCManager;

public class MyuserSnackOrderDAO implements DAO {
	private Connection getConnection() {
		OracleJDBCManager manager = new OracleJDBCManager();
		String oracleId = "s15010924";
		String passwd = "s15010924";
		int port = 1521;
		manager.registerOracleJDBCDriver();
		Connection conn = manager.connect(oracleId, passwd, port);
		return conn;
	}

	private MyuserSnackOrderDAO() {
	}

	private static MyuserSnackOrderDAO instance = new MyuserSnackOrderDAO();

	public static MyuserSnackOrderDAO getInstance() {
		return instance;
	}

	@Override
	public void list() {

		ArrayList<MyuserSnackOrderDTO> arrayList = new ArrayList<>();

		Connection conn = getConnection();
		PreparedStatement pstm = null;
		ResultSet result = null;
		String query = "select * from myuser_snack_order";

		try {
			pstm = conn.prepareStatement(query);
			result = pstm.executeQuery();
			while (result.next()) {
				arrayList.add(new MyuserSnackOrderDTO(result.getInt("ORDER_NUM"), result.getString("MYUSER_ID"),
						result.getString("SNACK_CODE"), result.getDate("ORDER_DATE")));
			}
			for (int i = 0; i < arrayList.size(); i++) {
				System.out.println(arrayList.get(i).toString());
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

	}

	public void listMe(String id) {

		OracleJDBCManager manager = new OracleJDBCManager();
		String oracleId = "s15010924";
		String passwd = "s15010924";
		int port = 1521;
		manager.registerOracleJDBCDriver();

		ArrayList<MyuserSnackOrderDTO> arrayList = new ArrayList<>();

		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet result = null;
		String query = "select * from myuser_snack_order where MYUSER_ID = ?";

		conn = manager.connect(oracleId, passwd, port);
		try {
			pstm = conn.prepareStatement(query);
			pstm.setString(1, ExecuteProject.id);
			result = pstm.executeQuery();
			while (result.next()) {
				arrayList.add(new MyuserSnackOrderDTO(result.getInt("ORDER_NUM"), result.getString("MYUSER_ID"),
						result.getString("SNACK_CODE"), result.getDate("ORDER_DATE")));
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
