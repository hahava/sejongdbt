package dao.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.user.MyuserSnackOrderDTO;
import dto.user.SnackInfoDTO;
import oracle.connect.OracleJDBCManager;

public class MyuserSnackOrderDAO implements DAO {

	@Override
	public void list() {

		OracleJDBCManager manager = new OracleJDBCManager();
		String oracleId = "s15010924";
		String passwd = "s15010924";
		int port = 1521;
		manager.registerOracleJDBCDriver();

		ArrayList<MyuserSnackOrderDTO> arrayList = new ArrayList<>();

		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet result = null;
		String query = "select * from myuser_snack_order";

		conn = manager.connect(oracleId, passwd, port);
		try {
			pstm = conn.prepareStatement(query);
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
