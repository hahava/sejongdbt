package kr.ac.sejong.feat.employeetask

import kr.ac.sejong.config.JDBCManager

class EmployeeTaskDAO private constructor() {
    companion object {
        @JvmStatic
        var instance: EmployeeTaskDAO? = null
            get() {
                if (field == null) {
                    field = EmployeeTaskDAO()
                }
                return field
            }
            private set
    }

    fun selectEmployeeTasks(): List<EmployeeTaskDTO> {
        val query = """
            SELECT
                EMPLOYEE_ROLE,
                EMPLOYEE_TASK_CON,
                EMPLOYEE_TASK_SAL
            FROM
                EMPLOYEE_TASK
        """

        return JDBCManager
            .getInstance()
            .queryForList(query, EmployeeTaskDTO::class.java)
    }

}