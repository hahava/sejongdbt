package dao.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import dto.admin.EmployeeDTO;
import dto.admin.EmployeeTaskDTO;
import oracle.connect.JDBCManager;

public class EmployeeDAO implements DAO {

	private EmployeeDAO() {

	}

	// 객체를 생성한다.
	private static EmployeeDAO instance = new EmployeeDAO();

	// 객체를 리턴한다.
	public static EmployeeDAO getInstance() {
		return instance;
	}

	// 오라클 드라이버 로드한다.
	private Connection getConnection() {
		JDBCManager manager = new JDBCManager();
		String oracleId = "s15010924";
		String passwd = "s15010924";
		int port = 1521;

		Connection conn = manager.connect(oracleId, passwd, port);
		return conn;
	}

	// 직원 테이블를 출력한다.
	public void list() {

		ArrayList<EmployeeDTO> arrayList = new ArrayList<>();

		Connection conn = getConnection();
		PreparedStatement pstm = null;
		ResultSet result = null;
		String query = "select EMPLOYEE_NUM,EMPLOYEE_NAME, " + "EMPLOYEE_AGE, EMPLOYEE_GENDER, EMPLOYEE_ROLE,"
				+ "EMPLOYEE_HIREDATE,EMPLOYEE_PUN_CNT  from EMPLOYEE";

		try {
			pstm = conn.prepareStatement(query);
			result = pstm.executeQuery();
			while (result.next()) {
				arrayList.add(new EmployeeDTO(result.getInt("EMPLOYEE_NUM"), result.getString("EMPLOYEE_NAME"), result.getInt("EMPLOYEE_AGE"),
						result.getString("EMPLOYEE_GENDER"), result.getString("EMPLOYEE_ROLE"), result.getDate("EMPLOYEE_HIREDATE"),
						result.getInt("EMPLOYEE_PUN_CNT")));
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
		// 결과를 전부 출력한다.
		for (int i = 0; i < arrayList.size(); i++) {
			System.out.println(arrayList.get(i).toString());
		}
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
			employeeSearch_allInfo();
			break;
		case 2:
			employeeSearch_dep();
			break;
		case 3:
			employeeSearch_age_sal();
			break;

		case 4:
			employeeSearch_sal();
			break;
		case 5:
			employeeSearch_pun_mem();
			break;
		case 6:
			return;
		default:
			System.out.println("잘못된 번호를  입력하셨습니다.");
		}
	}

	public void employeeSearch_allInfo() {

		String employeeName = null;

		Scanner sc = new Scanner(System.in);

		System.out.println("직원을 검색합니다. 이름을 입력하세요.(*를 누르면 모든 정보를 출력합니다.)");

		employeeName = sc.nextLine();

		ArrayList<EmployeeDTO> employeeDTO = new ArrayList<EmployeeDTO>();
		ArrayList<EmployeeTaskDTO> employeetaskDTO = new ArrayList<EmployeeTaskDTO>();

		/*
		 * employee와 employee_task테이블을 조인(employee_role)해서 *을 입력한 경우는 별도의 추가 조건을
		 * 걸지 않고 결과를 보여준다. 만약, 관리자가 이름을 입력한 경우에는 추가조건에 이름이 들어간다.
		 */
		String query = "select employee_num,employee_name,employee_age,employee_gender," + "emp.employee_role,employee_hiredate,employee_pun_cnt,"
				+ "employee_task_con,employee_task_sal from employee emp, employee_task emp_t " + "where emp.employee_role=emp_t.employee_role";
		if (employeeName.compareTo("*") != 0) {
			query += " and emp.employee_name='" + employeeName + "'";
		}

		Connection conn = getConnection();
		PreparedStatement pstm = null;
		ResultSet result = null;

		try {
			pstm = conn.prepareStatement(query);
			result = pstm.executeQuery();

			while (result.next()) {

				employeeDTO.add(new EmployeeDTO(result.getInt("employee_num"), result.getString("employee_name"), result.getInt("employee_age"),
						result.getString("employee_gender"), result.getString("employee_role"), result.getDate("employee_hiredate"),
						result.getInt("employee_pun_cnt")));

				employeetaskDTO.add(new EmployeeTaskDTO(result.getString("employee_role"), result.getString("employee_task_con"),
						result.getInt("employee_task_sal")));

			}
			if (employeeDTO.isEmpty()) {
				System.out.println("해당하는 직원이 없습니다.");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		try {
			result.close();
			pstm.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		for (int i = 0; i < employeeDTO.size(); i++) {
			System.out.println(employeeDTO.get(i).toString());
			System.out.println(employeetaskDTO.get(i).toString());
		}

	}

	// 나이기반 검색을 담당하는 메서드로, 최소 나이와 최대 나이를 입력받은 뒤 해당 나이에 속하는 직원들의 연봉을 출력한다.
	public void employeeSearch_age_sal() {
		int agemin;
		int agemax;
		Scanner sc = new Scanner(System.in);
		System.out.println("나이 기반 연봉 평균입니다. 최소 나이와 최대 나이를 입력하세요.");
		agemin = sc.nextInt();
		agemax = sc.nextInt();
		String query;
		/*
		 * 관리자가 최소나이와 최대나이를 입력하면 해당 나이에 속하는 직원의 숫자와 연봉의 평균을 출력한다. 해당 나이에 속하는
		 * 직원들의 부서를 employee table에서 쿼리하여 연봉 정보가 나와있는 employee_task에서 평균을 내게 된다.
		 * sub query를 이용힙니다.
		 */
		query = "select count(*),avg(employee_task_sal) " + "from employee_task "
				+ "where employee_role in (select employee_role from employee where employee_age between " + agemin + " and +" + agemax + ")";

		Connection conn = getConnection();
		PreparedStatement pstm = null;
		ResultSet result = null;

		try {
			pstm = conn.prepareStatement(query);
			result = pstm.executeQuery();

			if (result.next()) {
				System.out.print("count : " + result.getInt(1) + "," + "\tavg : " + result.getInt(2) + "\n");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			result.close();
			pstm.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	// 관리자가 검색을 원하는 부서에 속하는 직원을 출력하는 메서드이다.
	public void employeeSearch_dep() {

		Connection conn = getConnection();
		PreparedStatement pstm = null;
		ResultSet result = null;

		String depName;

		ArrayList<EmployeeDTO> employeeDTO = new ArrayList<EmployeeDTO>();
		Scanner sc = new Scanner(System.in);
		System.out.println("검색하고자 하는 부서의 팀원을 출력합니다.");
		depName = sc.nextLine();
		String query = "select employee_num ,employee_name, employee_age," + " employee_gender, employee_role, employee_hiredate,"
				+ "employee_pun_cnt  from employee where employee_role='" + depName + "'";

		try {
			pstm = conn.prepareStatement(query);
			result = pstm.executeQuery();

			while (result.next()) {

				employeeDTO.add(new EmployeeDTO(result.getInt("employee_num"), result.getString("employee_name"), result.getInt("employee_age"),
						result.getString("employee_gender"), result.getString("employee_role"), result.getDate("employee_hiredate"),
						result.getInt("employee_pun_cnt")));

			}

			if (employeeDTO.size() == 0) {
				System.out.println("팀원 배정 전인 부서이거나 잘못 입력했습니다.");
				return;
			}

			System.out.println("직번\t이름\t나이\t성별\t부서\t고용일");

			for (int i = 0; i < employeeDTO.size(); i++) {
				System.out.println(employeeDTO.get(i).EMPLOYEE_NUM + "\t" + employeeDTO.get(i).EMPLOYEE_NAME + "\t" + employeeDTO.get(i).EMPLOYEE_AGE
						+ "\t" + employeeDTO.get(i).EMPLOYEE_GENDER + "\t" + employeeDTO.get(i).EMPLOYEE_ROLE + "\t"
						+ employeeDTO.get(i).EMPLOYEE_HIREDATE);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// 최소 연봉과 최대 연봉 범위에 속하는 직원들의 정보를 출력하는 메서드이다.
	public void employeeSearch_sal() {

		Connection conn = getConnection();
		PreparedStatement pstm = null;
		ResultSet result = null;

		int minsal;
		int maxsal;

		Scanner sc = new Scanner(System.in);
		System.out.println("지정 범위 내 연봉을 수령하는 직원들의 정보를 출력합니다. 단위는 만원");
		System.out.println("최소 연봉을 입력하세요");
		minsal = sc.nextInt();
		System.out.println("최대 연봉을 입력하세요");
		maxsal = sc.nextInt();

		String query = "select employee_num, employee_name, employee_age, employee_gender, employee_role, employee_hiredate,  employee_pun_cnt"
				+ " from employee where employee_role in (select employee_role from employee_task where employee_task_sal between " + minsal + " and "
				+ maxsal + ")";

		ArrayList<EmployeeDTO> employeeDTO = new ArrayList<EmployeeDTO>();

		try {
			pstm = conn.prepareStatement(query);
			result = pstm.executeQuery();
			while (result.next()) {

				employeeDTO.add(new EmployeeDTO(result.getInt("employee_num"), result.getString("employee_name"), result.getInt("employee_age"),
						result.getString("employee_gender"), result.getString("employee_role"), result.getDate("employee_hiredate"),
						result.getInt("employee_pun_cnt")));

			}
			if (employeeDTO.size() == 0) {
				System.out.println("범위를 다시 확인하세요.");
				return;
			}
			System.out.println("직번\t이름\t나이\t성별\t부서\t고용일");

			for (int i = 0; i < employeeDTO.size(); i++) {
				System.out.println(employeeDTO.get(i).EMPLOYEE_NUM + "\t" + employeeDTO.get(i).EMPLOYEE_NAME + "\t" + employeeDTO.get(i).EMPLOYEE_AGE
						+ "\t" + employeeDTO.get(i).EMPLOYEE_GENDER + "\t" + employeeDTO.get(i).EMPLOYEE_ROLE + "\t"
						+ employeeDTO.get(i).EMPLOYEE_HIREDATE);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	// 근무 태만으로 지적받은 직원의 정보를 출력합니다.
	public void employeeSearch_pun_mem() {
		System.out.println("근무태만 직원을 출력합니다.");
		System.out.println("직번\t이름\t직급\t근무 태만 횟수");

		Connection conn = getConnection();
		PreparedStatement pstm = null;
		ResultSet result = null;

		String query = "select employee_num, employee_name, employee_role, employee_pun_cnt " + "from employee " + "where employee_pun_cnt>0";

		try {
			pstm = conn.prepareStatement(query);
			result = pstm.executeQuery();
			while (result.next()) {
				System.out.println(result.getInt(1) + "\t" + result.getString(2) + "\t" + result.getString(3) + "\t" + result.getInt(4));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			result.close();
			pstm.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
