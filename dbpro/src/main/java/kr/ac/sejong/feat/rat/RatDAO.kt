package kr.ac.sejong.feat.rat

import kr.ac.sejong.config.JDBCManager
import org.apache.commons.lang3.StringUtils

class RatDAO private constructor() {
    companion object {
        @JvmStatic
        var instance: RatDAO? = null
            get() {
                if (field == null) {
                    field = RatDAO()
                }
                return field
            }
            private set
    }

    fun selectRatings(): List<RatDTO> {
        val query = """ 
           select
                MYUSER_ID,
                MOVIE_CODE,
                RAT_POINT,
                RAT_COMMENT
           from
                RAT
       """

        return JDBCManager
            .getInstance()
            .queryForList(query, RatDTO::class.java)
    }

    // 로그인한 유저의 통계 내역을 출력한다.
    fun selectRatListByUserId(id: String?): List<Map<String, Any>> {
        /*
         * movie, rat 테이블을 조인(movie_code)하고 로그인한 유저가 매긴 평점과 감상평을 출력해준다.
         */
        val query = """
            SELECT
            	r.myuser_id,
            	m.movie_code,
            	m.movie_title,
            	r.rat_point,
            	r.rat_comment
            FROM
            	movie m
            JOIN
            	rat r ON m.movie_code = r.movie_code 
            WHERE
            	r.myuser_id = $id
        """

        return JDBCManager
            .getInstance()
            .queryForMaps(query, arrayOf(
                "r.myuser_id",
                "m.movie_code",
                "m.movie_title",
                "r.rat_point",
                "r.rat_comment"
            ))
    }

    // 해당 영화의 평점의 평균과 감상평을 보여주는 메서드이다.
    fun selectMovieRat(movieTitle: String?): List<RatDTO> {
        /*
         * subquery를 이용해서 movie table에서 movie_code를 얻은 뒤 rat 테이블에서 movie_code로 그룹핑해 평균과 평점을 매긴 회원 수를 출력한다.
         *
         */
        val movieRatingQuery = """
            SELECT
            	AVG(RAT_POINT) AS AVG,
            	COUNT(*) AS COUNT
            FROM
            	RAT
            WHERE
            	MOVIE_CODE IN (SELECT MOVIE_CODE FROM MOVIE WHERE MOVIE_TITLE = $movieTitle
            GROUP BY
            	MOVIE_CODE
        """

        JDBCManager
            .getInstance()
            .queryForMap(movieRatingQuery, arrayOf("AVG", "COUNT"))
            .forEach { (key, value) -> println("$key : $value") }
        /*
         * 해당영화를 본 회원들의 아이디, 평점, 감상평을 출력한다.
         * subquery를 이용했다.
         * */
        val movieAudiencQuery = """ 
            SELECT
            	MYUSER_ID,
            	MOVIE_CODE,
            	RAT_POINT,
            	RAT_COMMENT
            FROM
            	RAT
            WHERE
            	MOVIE_CODE IN(SELECT MOVIE_CODE FROM MOVIE WHERE MOVIE_TITLE = $movieTitle
        """

        return JDBCManager
            .getInstance()
            .queryForList(movieAudiencQuery, RatDTO::class.java)
    }

}