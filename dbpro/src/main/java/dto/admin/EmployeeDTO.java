package dto.admin;

import java.sql.Date;

public class EmployeeDTO implements DTO {
	public int EMPLOYEE_NUM;
	public String EMPLOYEE_NAME;
	public int EMPLOYEE_AGE;
	public String EMPLOYEE_GENDER;
	public String EMPLOYEE_ROLE;
	public Date EMPLOYEE_HIREDATE;
	public int EMPLOYEE_PUN_CNT;

	@Override
	public String toString() {
		return "EmployeeDTO [EMPLOYEE_NUM=" + EMPLOYEE_NUM + ", EMPLOYEE_NAME=" + EMPLOYEE_NAME + ", EMPLOYEE_AGE="
				+ EMPLOYEE_AGE + ", EMPLOYEE_GENDER=" + EMPLOYEE_GENDER + ", EMPLOYEE_ROLE=" + EMPLOYEE_ROLE
				+ ", EMPLOYEE_HIREDATE=" + EMPLOYEE_HIREDATE + ", EMPLOYEE_PUN_CNT=" + EMPLOYEE_PUN_CNT + "]";
	}

	public EmployeeDTO(int eMPLOYEE_NUM, String eMPLOYEE_NAME, int eMPLOYEE_AGE, String eMPLOYEE_GENDER,
			String eMPLOYEE_ROLE, Date eMPLOYEE_HIREDATE, int eMPLOYEE_PUN_CNT) {
		super();
		EMPLOYEE_NUM = eMPLOYEE_NUM;
		EMPLOYEE_NAME = eMPLOYEE_NAME;
		EMPLOYEE_AGE = eMPLOYEE_AGE;
		EMPLOYEE_GENDER = eMPLOYEE_GENDER;
		EMPLOYEE_ROLE = eMPLOYEE_ROLE;
		EMPLOYEE_HIREDATE = eMPLOYEE_HIREDATE;
		EMPLOYEE_PUN_CNT = eMPLOYEE_PUN_CNT;
	}

}
