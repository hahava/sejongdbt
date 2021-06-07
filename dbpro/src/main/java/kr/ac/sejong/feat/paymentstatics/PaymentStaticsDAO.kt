package kr.ac.sejong.feat.paymentstatics

import kr.ac.sejong.config.JDBCManager

class PaymentStaticsDAO private constructor() {
    companion object {
        @JvmStatic
        var instance: PaymentStaticsDAO? = null
            get() {
                if (field == null) {
                    field = PaymentStaticsDAO()
                }
                return field
            }
            private set
    }

    fun selectPaymentStatics(): List<PaymentStaticsDTO> {
        val query = """
            SELECT
                PAYMENT_DATE,
                PAYMENT_SUM,
                PAYMENT_COUNT,
                MYUSER_COUNT
            FROM
                PAYMENT_STATICS
        """

        return JDBCManager
            .getInstance()
            .queryForList(query, PaymentStaticsDTO::class.java)
    }
}