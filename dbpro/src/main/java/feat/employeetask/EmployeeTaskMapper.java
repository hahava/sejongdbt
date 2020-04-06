package feat.employeetask;

import menu.MenuMapper;
import menu.MenuMapping;

import java.util.List;

@MenuMapper
public class EmployeeTaskMapper {
	@MenuMapping("직원 업무 내역")
	public void getEmployeeTasks() {
		List<EmployeeTaskDTO> tasks = EmployeeTaskDAO.getInstance().selectEmployeeTasks();
		tasks.forEach(employeeTaskDTO -> System.out.println(employeeTaskDTO.toString()));
	}
}
