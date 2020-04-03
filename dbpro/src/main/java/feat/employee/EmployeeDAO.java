package feat.employee;

import oracle.connect.JDBCManager;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;

public class EmployeeDAO {

	private EmployeeDAO() {
	}

	private static EmployeeDAO instance;

	public static EmployeeDAO getInstance() {
		if (instance == null) {
			instance = new EmployeeDAO();
		}
		return instance;
	}

	// 직원 테이블를 출력한다.
	public List<EmployeeDTO> selectEmployees() {
		final String query = "SELECT " +
			"	EMPLOYEE_NUM, " +
			"	EMPLOYEE_NAME, " +
			"	EMPLOYEE_AGE, " +
			"	EMPLOYEE_GENDER, " +
			"	EMPLOYEE_ROLE, " +
			"	EMPLOYEE_HIREDATE, " +
			"	EMPLOYEE_PUN_CNT " +
			"FROM " +
			"	EMPLOYEE";

		return JDBCManager
			.getInstance()
			.queryForList(query, EmployeeDTO.class);
	}

	public List<EmployeeDTO> selectEmployeeByName(final String employeeName) {
		String query = "select " +
			"	EMPLOYEE_NUM, " +
			"	EMPLOYEE_NAME, " +
			"	EMPLOYEE_AGE, " +
			"	EMPLOYEE_GENDER, " +
			"	EMPLOYEE_ROLE, " +
			"	EMPLOYEE_HIREDATE, " +
			"	EMPLOYEE_PUN_CNT " +
			"FROM " +
			"	EMPLOYEE ";
		if (StringUtils.equals(employeeName, "*") == false) {
			query += "WHERE EMPLOYEE_NAME='" + employeeName + "'";
		}

		return JDBCManager
			.getInstance()
			.queryForList(query, EmployeeDTO.class);
	}

	// 나이기반 검색을 담당하는 메서드로, 최소 나이와 최대 나이를 입력받은 뒤 해당 나이에 속하는 직원들의 연봉을 출력한다.
	public Map<String, Object> selectSalaryAverage(final int minAge, final int maxAge, final String params[]) {
		final String query = "SELECT " +
			"	COUNT(*) AS count, " +
			"	AVG(EMPLOYEE_TASK_SAL) AS average " +
			"FROM " +
			"	EMPLOYEE EM " +
			"JOIN " +
			"	EMPLOYEE_TASK EMT " +
			"ON " +
			"	EM.EMPLOYEE_ROLE = EMT.EMPLOYEE_ROLE " +
			"WHERE " +
			"	EMPLOYEE_AGE BETWEEN " + minAge + " AND " + maxAge;

		return JDBCManager.getInstance().queryForMap(query, params);
	}

	// 관리자가 검색을 원하는 부서에 속하는 직원을 출력하는 메서드이다.
	public List<EmployeeDTO> selectEmployeeByEmployeeRole(final String employeeRole) {
		final String query = "SELECT " +
			"    EMPLOYEE_NUM, " +
			"    EMPLOYEE_NAME, " +
			"    EMPLOYEE_AGE, " +
			"    EMPLOYEE_GENDER, " +
			"    EMPLOYEE_ROLE, " +
			"    EMPLOYEE_HIREDATE, " +
			"    EMPLOYEE_PUN_CNT " +
			"FROM " +
			"    employee " +
			"WHERE " +
			"    employee_role = '" + employeeRole + "'";

		return JDBCManager
			.getInstance()
			.queryForList(query, EmployeeDTO.class);
	}

	// 최소 연봉과 최대 연봉 범위에 속하는 직원들의 정보를 출력하는 메서드이다.
	public List<Map<String, Object>> selectEmployeeBySalaryRange(final int minSalary, final int maxSalary,
		final String[] params) {

		final String query = "SELECT " +
			"	EMPLOYEE_NAME as employeeName," +
			"	EMPLOYEE_TASK_SAL as employeeTaskSal" +
			"FROM" +
			"	employee em" +
			"JOIN" +
			"	EMPLOYEE_TASK EMT ON EM.EMPLOYEE_ROLE = EMT.EMPLOYEE_ROLE" +
			"WHERE" +
			"	EMPLOYEE_TASK_SAL > " + minSalary +
			"AND " +
			"	EMPLOYEE_TASK_SAL < " + maxSalary;

		return JDBCManager.getInstance().queryForMaps(query, params);
	}

	// 근무 태만으로 지적받은 직원의 정보를 출력합니다.
	public List<EmployeeDTO> selectPunishmentMember() {
		final String query = "SELECT " +
			"	EMPLOYEE_NUM, " +
			"	EMPLOYEE_NAME, " +
			"	EMPLOYEE_ROLE, " +
			"	EMPLOYEE_PUN_CNT " +
			"FROM " +
			"	EMPLOYEE " +
			"WHERE EMPLOYEE_PUN_CNT > 0";

		return JDBCManager
			.getInstance()
			.queryForList(query, EmployeeDTO.class);
	}

}
