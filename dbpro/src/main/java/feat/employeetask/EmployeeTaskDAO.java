package feat.employeetask;

import oracle.connect.JDBCManager;

import java.util.List;

public class EmployeeTaskDAO {

	private EmployeeTaskDAO() {
	}

	private static EmployeeTaskDAO instance;

	public static EmployeeTaskDAO getInstance() {
		if (instance == null) {
			instance = new EmployeeTaskDAO();
		}
		return instance;
	}

	public List<EmployeeTaskDTO> selectEmployeeTasks() {
		final String query = "SELECT " +
			"	EMPLOYEE_ROLE, " +
			"	EMPLOYEE_TASK_CON, " +
			"	EMPLOYEE_TASK_SAL " +
			"FROM " +
			"	EMPLOYEE_TASK";

		return JDBCManager
			.getInstance()
			.queryForList(query, EmployeeTaskDTO.class);
	}

}
