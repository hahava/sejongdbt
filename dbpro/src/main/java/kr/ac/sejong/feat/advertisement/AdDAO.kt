package kr.ac.sejong.feat.advertisement

import kr.ac.sejong.config.JDBCManager

class AdDAO private constructor() {
    companion object {
        @JvmStatic
        var instance: AdDAO? = null
            get() {
                if (field == null) {
                    field = AdDAO()
                }
                return field
            }
            private set
    }

    fun selectAdvertisements(): List<AdDTO> {
        val query = """
            SELECT 
                AD_TITLE,
                AD_COMPANY,
                AD_DATE,
                AD_PRICE
            FROM
            	AD
        """

        return JDBCManager
            .getInstance()
            .queryForList(query, AdDTO::class.java)
    }
}