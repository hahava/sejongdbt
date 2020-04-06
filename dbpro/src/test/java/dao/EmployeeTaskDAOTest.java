package dao;

import feat.employeetask.EmployeeTaskDAO;
import org.junit.Test;

public class EmployeeTaskDAOTest {
	@Test
	public void getEmployeeTaskTest() {
		EmployeeTaskDAO.getInstance().selectEmployees();
	}
}
