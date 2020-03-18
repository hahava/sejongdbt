package dao.admin;

import dto.admin.EmployeeDTO;
import oracle.connect.JDBCManager;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class EmployeeDAO implements DAO {

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
	public void list() {
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
		JDBCManager
			.getInstance()
			.queryForList(query, EmployeeDTO.class)
			.forEach(employeeDTO -> System.out.println(employeeDTO));
	}

	// 직원 정보를 조회 할 수 있는 메뉴이다.
	public void employeeSearchMenu() {
		int selectMenu = 0;
		Scanner sc = new Scanner(System.in);

		System.out.println("환영합니다! 직원정보관리메뉴입니다.");
		System.out.println("1. 직원 정보 검색(전체/이름기반)");
		System.out.println("2. 부서 별 직원 검색");
		System.out.println("3. 나이 기반 연봉 평균");
		System.out.println("4. 지정 연봉 이상 직원");
		System.out.println("5. 근태직원 조회");

		System.out.println("6. 뒤로");

		selectMenu = sc.nextInt();
		switch (selectMenu) {
			case 1:
				//getEmployee();
				break;
			case 2:
				//				getEmployeeByEmployeeRole();
				break;
			case 3:
				//				getSalaryAverage();
				break;

			case 4:
				//				getEmployeeBySalaryRange();
				break;
			case 5:
				getPunishmentMember();
				break;
			case 6:
				return;
			default:
				System.out.println("잘못된 번호를  입력하셨습니다.");
		}
	}

	public void getEmployee(final String employeeName) {

		System.out.println("직원을 검색합니다. 이름을 입력하세요.(*를 누르면 모든 정보를 출력합니다.)");

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

		JDBCManager
			.getInstance()
			.queryForList(query, EmployeeDTO.class)
			.forEach(employeeDTO -> System.out.println(employeeDTO.toString()));

	}

	// 나이기반 검색을 담당하는 메서드로, 최소 나이와 최대 나이를 입력받은 뒤 해당 나이에 속하는 직원들의 연봉을 출력한다.
	public void getSalaryAverage(final int minAge, final int maxAge) {
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

		Map<String, Object> result = JDBCManager.getInstance().queryForMap(query, new String[] {"count", "average"});
		System.out.println(result.get("count") + "명\t" + result.get("average") + " 원");
	}

	// 관리자가 검색을 원하는 부서에 속하는 직원을 출력하는 메서드이다.
	public void getEmployeeByEmployeeRole(final String employeeRole) {
		System.out.println("검색하고자 하는 부서의 팀원을 출력합니다.");

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

		JDBCManager
			.getInstance()
			.queryForList(query, EmployeeDTO.class)
			.forEach(employeeDTO -> System.out.println(employeeDTO.toString()));
	}

	// 최소 연봉과 최대 연봉 범위에 속하는 직원들의 정보를 출력하는 메서드이다.
	public void getEmployeeBySalaryRange(final int minSalary, final int maxSalary) {

		System.out.println("지정 범위 내 연봉을 수령하는 직원들의 정보를 출력합니다. 단위는 만원");
		System.out.println("최소 연봉을 입력하세요");
		System.out.println("최대 연봉을 입력하세요");

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

		List<Map<String, Object>> results = JDBCManager.getInstance()
			.queryForMaps(query, new String[] {"employeeName", "employeeTaskSal"});
		for (Map<String, Object> map : results) {
		System.out.println("employeeName : " + map.get("employeeName") + " \t " + "salary : " + map.get("employeeTaskSal"));
		}
	}

	// 근무 태만으로 지적받은 직원의 정보를 출력합니다.
	public void getPunishmentMember() {

		final String query = "SELECT " +
			"	EMPLOYEE_NUM, " +
			"	EMPLOYEE_NAME, " +
			"	EMPLOYEE_ROLE, " +
			"	EMPLOYEE_PUN_CNT " +
			"FROM " +
			"	EMPLOYEE " +
			"WHERE EMPLOYEE_PUN_CNT > 0";

		JDBCManager
			.getInstance()
			.queryForList(query, EmployeeDTO.class)
			.forEach(employeeDTO -> System.out.println(employeeDTO));
	}

}
