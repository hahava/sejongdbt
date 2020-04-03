package dao;

import dao.admin.EmployeeTaskDAO;
import org.junit.Test;

public class EmployeeTaskDAOTest {
	@Test
	public void getEmployeeTaskTest() {
		EmployeeTaskDAO.getInstance().selectEmployees();
	}
}
