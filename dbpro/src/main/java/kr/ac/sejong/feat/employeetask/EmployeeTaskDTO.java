package kr.ac.sejong.feat.employeetask;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import kr.ac.sejong.config.ColumnName;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeTaskDTO {
	@ColumnName("EMPLOYEE_ROLE")
	private String employeeRole;

	@ColumnName("EMPLOYEE_TASK_CON")
	private String employeeTaskCon;

	@ColumnName("EMPLOYEE_TASK_SAL")
	private int employeeTaskSal;
}
