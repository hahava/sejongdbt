package dao.admin;

import dto.admin.EmployeeTaskDTO;
import oracle.connect.JDBCManager;

public class EmployeeTaskDAO implements DAO {

	private EmployeeTaskDAO() {
	}

	private static EmployeeTaskDAO instance;

	public static EmployeeTaskDAO getInstance() {
		if (instance == null) {
			instance = new EmployeeTaskDAO();
		}
		return instance;
	}

	@Override
	public void list() {
		final String query = "SELECT " +
			"	EMPLOYEE_ROLE, " +
			"	EMPLOYEE_TASK_CON, " +
			"	EMPLOYEE_TASK_SAL " +
			"FROM " +
			"	EMPLOYEE_TASK";

		JDBCManager
			.getInstance()
			.queryForList(query, EmployeeTaskDTO.class)
			.forEach(employeeTaskDTO -> System.out.println(employeeTaskDTO.toString()));
	}

}