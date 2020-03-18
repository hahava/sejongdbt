package dao;

import dao.admin.EmployeeDAO;
import org.junit.Test;

public class EmployeeDAOTest {
	@Test
	public void getEmployeesTest() {
		EmployeeDAO.getInstance().list();
	}

	@Test
	public void getPunishmentMemberTest() {
		EmployeeDAO.getInstance().getPunishmentMember();
	}

	@Test
	public void getAverageSalary() {
		EmployeeDAO.getInstance().getSalaryAverage(20, 30);
	}

	@Test
	public void getEmployeeByEmployeeRoleTest() {
		EmployeeDAO.getInstance().getEmployeeByEmployeeRole("사장");
	}

	@Test
	public void getEmployee() {
		EmployeeDAO.getInstance().getEmployee("이순규");
	}

	@Test
	public void getEmployeeSalaryByRangeTest() {
		EmployeeDAO.getInstance().getEmployeeBySalaryRange(3000, 6000);
	}

}
