package kr.ac.sejong.feat.payment

import kr.ac.sejong.config.JDBCManager
import org.apache.commons.lang3.StringUtils

class MoviePaymentDAO private constructor() {
    companion object {
        @JvmStatic
        var instance: MoviePaymentDAO? = null
            get() {
                if (field == null) {
                    field = MoviePaymentDAO()
                }
                return field
            }
            private set
    }

    fun selectMoviePayments(): List<MoviePaymentDTO> {
        val query = """ 
            SELECT
                MOVIE_PAYMENT_CODE,
                MYUSER_ID,
                MOVIE_CODE,
                PAYMENT_CODE,
                PAYMENT_DATE
            FROM
                MOVIE_PAYMENT
        """

        return JDBCManager
            .getInstance()
            .queryForList(query, MoviePaymentDTO::class.java)
    }

    fun selectMyMoviePayments(id: String?): List<Map<String, Any>> {
        val query = """
            SELECT
                mp.movie_payment_code,
                mp.myuser_id,
                m.movie_title, 
                m.movie_age,
                p.payment_way,
                mp.payment_date
            FROM
                movie_payment mp
            JOIN
                movie m ON mp.movie_code = m.movie_code
            JOIN
                payment p ON mp.payment_code = p.payment_code
            WHERE
                mp.myuser_id = $id
        """

        return JDBCManager
            .getInstance()
            .queryForMaps(query, arrayOf("mp.movie_payment_code",
                "mp.myuser_id",
                "m.movie_title",
                "m.movie_age",
                "p.payment_way",
                "mp.payment_date"
            ))
    }
}