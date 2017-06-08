package dao.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.admin.AdDTO;
import dto.admin.EmployeeTaskDTO;
import oracle.connect.OracleJDBCManager;

public class EmployeeTaskDAO implements DAO {
	private Connection getConnection() {
		OracleJDBCManager manager = new OracleJDBCManager();
		String oracleId = "s15010924";
		String passwd = "s15010924";
		int port = 1521;
		manager.registerOracleJDBCDriver();
		Connection conn = manager.connect(oracleId, passwd, port);
		return conn;
	}

	private EmployeeTaskDAO() {
	}

	private static EmployeeTaskDAO instance = new EmployeeTaskDAO();

	public static EmployeeTaskDAO getnstance() {
		return instance;
	}

	//직무내역을 전부 출력한다.
	@Override
	public void list() {
		// TODO Auto-generated method stub

		ArrayList<EmployeeTaskDTO> arrayList = new ArrayList<>();

		Connection conn = getConnection();
		PreparedStatement pstm = null;
		ResultSet result = null;

		String query = "select EMPLOYEE_ROLE, EMPLOYEE_TASK_CON, EMPLOYEE_TASK_SAL from EMPLOYEE_TASK";

		try {
			pstm = conn.prepareStatement(query);
			result = pstm.executeQuery();
			while (result.next()) {
				arrayList.add(new EmployeeTaskDTO(result.getString("EMPLOYEE_ROLE"), result.getString("EMPLOYEE_TASK_CON"),
						result.getInt("EMPLOYEE_TASK_SAL")));
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
