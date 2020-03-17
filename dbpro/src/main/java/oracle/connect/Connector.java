package oracle.connect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Connector {

	public void check() {

		Connection conn = null;
		JDBCManager manager = new JDBCManager();

		String oracleId = "s15010924";
		String passwd = "s15010924";
		int port = 1521;


		conn = manager.connect(oracleId, passwd, port);
		PreparedStatement pstm = null;
		String query = "select * from tab"; // Whatever you want to inquiry
		ResultSet result = null;

		try {
			pstm = conn.prepareStatement(query);
			result = pstm.executeQuery();
			while (result.next()) {
				System.out.println(result.getString(1));
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
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
}