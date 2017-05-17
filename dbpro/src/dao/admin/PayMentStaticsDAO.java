package dao.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.admin.PaymentStaticsDTO;
import dto.user.MyuserSnackOrderDTO;
import oracle.connect.OracleJDBCManager;

public class PayMentStaticsDAO implements DAO {

	@Override
	public void list() {
		// TODO Auto-generated method stub
		OracleJDBCManager manager = new OracleJDBCManager();
		String oracleId = "s15010924";
		String passwd = "s15010924";
		int port = 1521;
		manager.registerOracleJDBCDriver();

		ArrayList<PaymentStaticsDTO> arrayList = new ArrayList<>();

		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet result = null;
		String query = "select * from Payment_statics";

		conn = manager.connect(oracleId, passwd, port);
		try {
			pstm = conn.prepareStatement(query);
			result = pstm.executeQuery();
			while (result.next()) {
				arrayList.add(new PaymentStaticsDTO(result.getDate("PAYMENT_DATE"), result.getInt("PAYMENT_SUM"),
						result.getInt("PAYMENT_COUNT"), result.getInt("MYUSER_COUNT")));
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
