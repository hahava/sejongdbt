package kr.ac.sejong.feat.employee;

import kr.ac.sejong.menu.MenuMapper;
import kr.ac.sejong.menu.MenuMapping;
import kr.ac.sejong.menu.MenuSelector;
import org.apache.commons.lang3.StringUtils;
import kr.ac.sejong.util.ConsoleUtil;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@MenuMapper
public class EmployeeMapper {

	@MenuMapping("직원관련 검색")
	public void employeeSearchMenu() throws IOException {
		while (true) {
			System.out.println("환영합니다! 직원정보관리메뉴입니다.");
			System.out.println("1. 직원 정보 검색(전체/이름기반)");
			System.out.println("2. 부서 별 직원 검색");
			System.out.println("3. 나이 기반 연봉 평균");
			System.out.println("4. 지정 연봉 이상 직원");
			System.out.println("5. 근태직원 조회");
			System.out.println("6. 뒤로");

			String requestMenu = ConsoleUtil.readString();
			if (StringUtils.equals(requestMenu, "뒤로")) {
				break;
			}
			MenuSelector.getInstance().select(requestMenu);
		}
	}

	@MenuMapping("직원 정보")
	public void getEmployees() {
		List<EmployeeDTO> employees = EmployeeDAO.getInstance().selectEmployees();
		employees.forEach(employeeDTO -> System.out.println(employeeDTO.toString()));
	}

	@MenuMapping("직원 정보 검색")
	public void getEmployeeByName() throws IOException {
		String employeeName = ConsoleUtil.readString("직원을 검색합니다. 이름을 입력하세요.(*를 누르면 모든 정보를 출력합니다.)");
		List<EmployeeDTO> employees = EmployeeDAO.getInstance().selectEmployeeByName(employeeName);
		employees.forEach(employeeDTO -> System.out.println(employeeDTO.toString()));
	}

	@MenuMapping("부서 별 직원 검색")
	public void getEmployeesByRole() throws IOException {
		String employeeRole = ConsoleUtil.readString("검색하고자 하는 부서의 팀원을 출력합니다");
		List<EmployeeDTO> employees = EmployeeDAO.getInstance().selectEmployeeByEmployeeRole(employeeRole);
		employees.forEach(employeeDTO -> System.out.println(employeeDTO.toString()));
	}

	@MenuMapping("나이 기반 연봉 평균")
	public void getSalaryAverage() throws IOException {
		ConsoleUtil.printLn("나이 기반 연봉 평균입니다.");

		int minAge = ConsoleUtil.readInt("최소 나이를 입력해주세요");
		int maxAge = ConsoleUtil.readInt("최대 나이를 입력해주세요");
		String[] params = new String[] {"count", "average"};

		Map<String, Object> employees = EmployeeDAO.getInstance().selectSalaryAverage(minAge, maxAge, params);
		System.out.println(employees.get("count") + "명\t" + employees.get("average") + " 원");
	}

	@MenuMapping("지정 연봉 이상 직원")
	public void getEmployeeBySalaryRange() throws IOException {
		ConsoleUtil.printLn("지정 범위 내 연봉을 수령하는 직원들의 정보를 출력합니다. 단위는 만원");

		int minSalary = ConsoleUtil.readInt("최소 연봉을 입력하세요");
		int maxSalary = ConsoleUtil.readInt("최대 연봉을 입력하세요");
		String[] params = new String[] {"employeeName", "employeeTaskSal"};

		List<Map<String, Object>> results = EmployeeDAO.getInstance()
			.selectEmployeeBySalaryRange(minSalary, maxSalary, params);
		for (Map<String, Object> map : results) {
			ConsoleUtil.printLn(
				"employeeName : " + map.get("employeeName") + " \t " + "salary : " + map.get("employeeTaskSal"));
		}
	}

	@MenuMapping("근태직원 조회")
	public void getPunishmentMember() {
		List<EmployeeDTO> employees = EmployeeDAO.getInstance().selectPunishmentMember();
		employees.forEach(employeeDTO -> System.out.println(employeeDTO.toString()));
	}

}
