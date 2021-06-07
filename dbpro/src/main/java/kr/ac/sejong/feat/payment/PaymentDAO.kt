package kr.ac.sejong.feat.payment

import kr.ac.sejong.config.JDBCManager

class PaymentDAO private constructor() {
    companion object {
        @JvmStatic
        var instance: PaymentDAO? = null
            get() {
                if (field == null) {
                    field = PaymentDAO()
                }
                return field
            }
            private set
    }

    // 결제 방법을 출력한다.
    fun selectPaymentWays(): List<PaymentDTO> {
        val query = """
            SELECT 
                PAYMENT_CODE, 
                PAYMENT_WAY 
            FROM 
                PAYMENT
        """

        return JDBCManager
            .getInstance()
            .queryForList(query, PaymentDTO::class.java)
    }

    // 예매 목록을 코드를 이용하여 삭제 하는 메뉴이다.
    fun deletePayment(movieReservationCode: Int) {
        val query = """
            DELETE
            FROM
                MOVIE_PAYMENT
            WHERE
                MOVIE_PAYMENT_CODE = ?
        """

        val result = JDBCManager
            .getInstance()
            .delete(query, arrayOf<Any>(movieReservationCode))
    }

    // 영화 수정을 할 수 있는 메뉴이다.
    fun updateMovieReservation(moviePaymentDTO: MoviePaymentDTO) {
        val query = """ 
           UPDATE
                MOVIE_PAYMENT
           SET
                MOVIE_CODE = ?,
                PAYMENT_CODE = ?,
                PAYMENT_DATE = ?
           WHERE
                MOVIE_PAYMENT_CODE = ?
        """

        JDBCManager.getInstance().update(query, arrayOf(
            moviePaymentDTO.movieCode,
            moviePaymentDTO.paymentCode,
            moviePaymentDTO.paymentDate,
            moviePaymentDTO.moviePaymentCode
        ))
    }

    //예약할 수 있는 메뉴
    fun insertMovieReservation(moviePaymentDTO: MoviePaymentDTO): Int {
        val query = """
            INSERT INTO
                MOVIE_PAYMENT
            VALUES
                (DEFAULT,?,?,?,?)
        """

        return JDBCManager
            .getInstance()
            .insert(query, arrayOf(
                moviePaymentDTO.myuserId,
                moviePaymentDTO.movieCode,
                moviePaymentDTO.paymentCode,
                moviePaymentDTO.paymentDate
            ))
    }
}