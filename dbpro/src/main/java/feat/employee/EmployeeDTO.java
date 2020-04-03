package feat.employee;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import oracle.connect.ColumnName;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {
	@ColumnName("EMPLOYEE_NUM")
	private int employeeNum;

	@ColumnName("EMPLOYEE_NAME")
	private String employeeName;

	@ColumnName("EMPLOYEE_AGE")
	private int employeeAge;

	@ColumnName("EMPLOYEE_GENDER")
	private String employeeGender;

	@ColumnName("EMPLOYEE_ROLE")
	private String employeeRole;

	@ColumnName("EMPLOYEE_HIREDATE")
	private Date employeeHiredate;

	@ColumnName("EMPLOYEE_PUN_CNT")
	private int employeePunCnt;
}
