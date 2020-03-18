package dto.admin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import oracle.connect.ColumnName;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeTaskDTO {
	@ColumnName("EMPLOYEE_ROLE")
	public String employeeRole;

	@ColumnName("EMPLOYEE_TASK_CON")
	public String employeeTaskCon;

	@ColumnName("EMPLOYEE_TASK_SAL")
	public int employeeTaskSal;
}
