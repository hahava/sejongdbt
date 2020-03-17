package dto.admin;

public class EmployeeTaskDTO implements DTO {
	public String EMPLOYEE_ROLE;
	public String EMPLOYEE_TASK_CON;
	public int EMPLOYEE_TASK_SAL;

	@Override
	public String toString() {
		return "EmployeeTaskDTO [EMPLOYEE_ROLE=" + EMPLOYEE_ROLE + ", EMPLOYEE_TASK_CON=" + EMPLOYEE_TASK_CON
				+ ", EMPLOYEE_TASK_SAL=" + EMPLOYEE_TASK_SAL + "]";
	}

	public EmployeeTaskDTO(String eMPLOYEE_ROLE, String eMPLOYEE_TASK_CON, int eMPLOYEE_TASK_SAL) {
		super();
		EMPLOYEE_ROLE = eMPLOYEE_ROLE;
		EMPLOYEE_TASK_CON = eMPLOYEE_TASK_CON;
		EMPLOYEE_TASK_SAL = eMPLOYEE_TASK_SAL;
	}

}
