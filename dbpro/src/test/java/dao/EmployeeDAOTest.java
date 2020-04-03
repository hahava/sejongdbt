package dao;

import feat.employee.EmployeeDAO;
import org.junit.Test;

public class EmployeeDAOTest {
	@Test
	public void getEmployeesTest() {
		EmployeeDAO.getInstance().selectEmployees();
	}

	@Test
	public void getPunishmentMemberTest() {
		EmployeeDAO.getInstance().selectPunishmentMember();
	}

	@Test
	public void getAverageSalary() {
		EmployeeDAO.getInstance().selectSalaryAverage(20, 30);
	}

	@Test
	public void getEmployeeByEmployeeRoleTest() {
		EmployeeDAO.getInstance().selectEmployeeByEmployeeRole("사장");
	}

	@Test
	public void getEmployee() {
		EmployeeDAO.getInstance().selectEmployeeByName("이순규");
	}

	@Test
	public void getEmployeeSalaryByRangeTest() {
		EmployeeDAO.getInstance().selectEmployeeBySalaryRange(3000, 6000);
	}

}
