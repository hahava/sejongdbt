package kr.ac.sejong.dao;

import kr.ac.sejong.feat.employeetask.EmployeeTaskDAO;
import org.junit.Test;

public class EmployeeTaskDAOTest {
	@Test
	public void getEmployeeTaskTest() {
		EmployeeTaskDAO.getInstance().selectEmployeeTasks();
	}
}
