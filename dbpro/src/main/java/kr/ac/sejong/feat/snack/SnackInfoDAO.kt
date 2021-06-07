package kr.ac.sejong.feat.snack

import kr.ac.sejong.config.JDBCManager

class SnackInfoDAO private constructor() {
    companion object {
        @JvmStatic
        var instance: SnackInfoDAO? = null
            get() {
                if (field == null) {
                    field = SnackInfoDAO()
                }
                return field
            }
            private set
    }

    // 스낵관련 정보를 전부 출력한다.
    fun selectSnacks(): List<SnackInfoDTO> {
        val query = """
            SELECT
            	SNACK_CODE,
            	SNACK_NAME,
            	SNACK_CONTENT,
            	SNACK_CAL,
            	SNACK_PRICE
            FROM
            	SNACK_INFO
        """
        
        return JDBCManager
            .getInstance()
            .queryForList(query, SnackInfoDTO::class.java)
    }
}