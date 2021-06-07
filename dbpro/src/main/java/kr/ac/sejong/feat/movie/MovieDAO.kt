package kr.ac.sejong.feat.movie

import kr.ac.sejong.config.JDBCManager
import java.util.*

class MovieDAO private constructor() {
    companion object {
        @JvmStatic
        var instance: MovieDAO? = null
            get() {
                if (field == null) {
                    field = MovieDAO()
                }
                return field
            }
            private set
    }

    // Movie 테이블의 내용을 전부 출력한다.
    fun selectMovies(): List<MovieDTO> {
        val query = """ 
           SELECT
            MOVIE_CODE,
            MOVIE_TITLE,
            MOVIE_DIRECTOR,
            MOVIE_AGE,
            MOVIE_GENRE,
            MOVIE_START,
            MOVIE_END
           FROM " +
            MOVIE"
        """

        return JDBCManager
            .getInstance()
            .queryForList(query, MovieDTO::class.java)
    }

    fun deleteMovie(movieCode: String): Int {
        val query = "DELETE FROM MOVIE WHERE MOVIE_CODE = ?"
        return JDBCManager
            .getInstance()
            .delete(query, arrayOf(movieCode))
    }

    // 영화 내용 변경하기
    fun updateMovie(movie: MovieDTO) {
        // 해당 where movie_code를 입력받아 수정한다.
        val query = """
            UPDATE 
                MOVIE
            SET 
                MOVIE_TITLE = ?,
                MOVIE_DIRECTOR = ?,
                MOVIE_AGE= ?,
                MOVIE_GENRE= ?,
                MOVIE_START=?,
                MOVIE_END=?
            WHERE
                MOVIE_CODE =?
        """

        JDBCManager
            .getInstance()
            .update(query, arrayOf(
                movie.movieTitle,
                movie.movieDirector,
                movie.movieAge,
                movie.movieGenre,
                movie.movieStart,
                movie.movieEnd,
                movie.movieCode
            ))
    }

    // 영화 정보 보여주기 - 영화 개요 검색
    fun selectMoviesWithActors(movieTitle: String?): List<Map<String, Any>> {
        val query = """
            SELECT
                M.MOVIE_CODE,
                MOVIE_TITLE,
                MOVIE_DIRECTOR,
                MOVIE_AGE,
                MOVIE_GENRE,
                MOVIE_START,
                MOVIE_END,
                A.ACTOR_NAME,
                MA.ACTOR_ROLE,
                A.ACTOR_BIRTH,
                A.ACTOR_GENDER
            FROM
                MOVIE M, MOVIE_ACTOR MA, ACTOR A
            WHERE
            ${
        if (movieTitle != "*")
            """
                MOVIE_TITLE = $movieTitle 
            AND
            """
        else ""}
                MA.MOVIE_CODE = M.MOVIE_CODE
            AND
                MA.ACTOR_CODE = A.ACTOR_CODE
            ORDER BY M.MOVIE_CODE
        """

        return JDBCManager
            .getInstance()
            .queryForMaps(query, arrayOf("M.MOVIE_CODE",
                "MOVIE_TITLE",
                "MOVIE_DIRECTOR",
                "MOVIE_AGE",
                "MOVIE_GENRE",
                "MOVIE_START",
                "MOVIE_END",
                "A.ACTOR_NAME",
                "MA.ACTOR_ROLE",
                "A.ACTOR_BIRTH",
                "A.ACTOR_GENDER")
            )
    }

    // 특정 배우가 출연한 영화 검색
    fun selectMoviesByActor(actorName: String): List<Map<String, Any>> {
        val query = """
            SELECT
                mv.movie_title,
                kr.ac.actor_name,
                kr.ac.actor_birth,
                kr.ac.actor_gender,
                ma.actor_role
            FROM
                movie mv
            JOIN
                movie_actor ma ON mv.movie_code = ma.movie_code
            JOIN
                actor kr.ac ON kr.ac.actor_code = ma.actor_code
            WHERE 
                kr.ac.actor_name = $actorName
        """

        return JDBCManager
            .getInstance()
            .queryForMaps(
                query, arrayOf("mv.movie_title",
                "kr.ac.actor_name",
                "kr.ac.actor_birth",
                "kr.ac.actor_gender",
                "ma.actor_role")
            )
    }

    // 영화 예약 횟수가 가장 많은 회원의 id와 예약횟수를 출력한다.
    fun selectPersonWhoBookedTheMostMovies(): Map<String, Any> {
        val query = """
            SELECT
                m.myuser_id, COUNT(*) AS total_count
            FROM
                myuser m
            JOIN
                movie_payment mp ON m.myuser_id = mp.myuser_id
            GROUP BY 
                myuser_id
            ORDER BY 
                total_count DESC
            LIMIT 1
        """

        return JDBCManager
            .getInstance()
            .queryForMap(query, arrayOf("m.myuser_id", "total_count"))
    }

    // 특정 횟수를 입력하고 그 횟수보다 더 많이 예약한 회원의 id와 예약횟수를 출력한다.
    fun selectMovieReservationCountOfPerson(reservationCount: Int): List<Map<String, Any>> {
        val query = """ 
            SELECT
                m.myuser_id as userId,
                COUNT(*) as watchCount
            FROM
                myuser m,
                movie_payment mp
            WHERE
                m.myuser_id = mp.myuser_id
            GROUP BY m.myuser_id
            HAVING COUNT(*) >= $reservationCount
        """

        return JDBCManager.getInstance()
            .queryForMaps(query, arrayOf("userId", "watchCount"))
    }

    // 영화 별 평점통계와 광고비 통계를 낼 수 있는 메뉴이다.
    fun movieStaticsMenu() {
        var menu = 0
        val sc = Scanner(System.`in`)
        println("관리자용 영화 상세 통계 메뉴입니다.")
        println("1. 영화 별 평점 통계")
        println("2. 영화별 광고비 합계")
        println("3. 뒤로")
        menu = sc.nextInt()
        when (menu) {
            1 ->  // 영화별 평점 통계를 내는 메서드이다.
                selectMovieRatStatic(0.0)
            2 ->  // 영화에 할당된 광고비의 합계 통계를 내는 메서드이다.
                selectMovieADStatic()
            3 -> return
            else -> println("잘못 입력하셨습니다.")
        }
    }

    // 영화별 평점 통계를 내는 메서드이다.
    fun selectMovieRatStatic(score: Double): List<Map<String, Any>> {
        println("영화별 평점 통계입니다.")
        println("최소 범위를 입력해주세요.(0~5)(0 입력시, 전체 출력)")
        /*
         * 관리자가 최소 평점을 입력하면, 해당 평점 이상이 되는 영화의 movie_code, movie_title, 평점, 점수를 준
         * 사람들의 수를 출력한다. group by와 having이 사용되었다.
         */
        val query = """ 
             SELECT
                m.movie_code,
                m.movie_title,
                AVG(r.rat_point),
                COUNT(m.movie_code)
             FROM
                movie m 
             JOIN 
                rat r ON m.movie_code = r.movie_code 
             GROUP BY m.movie_code , m.movie_title
             HAVING AVG(r.rat_point)>= $score
        """
        return JDBCManager
            .getInstance()
            .queryForMaps(query, arrayOf("m.movie_code", "m.movie_title", "AVG(r.rat_point)", "COUNT(m.movie_code)"))
    }

    // 영화에 할당된 광고비의 합계 통계를 내는 메서드이다.
    fun selectMovieADStatic(): List<Map<String, Any>> {
        /*
         * movie와 movie_ad 테이블을 조인한 뒤 movie_code, movie_title로 grouping을 하고, 광고비 합계가 가장 많은 순서대로 정렬했다.
         * movie_code, movie_title, 광고비 합계, 해당 영화에 몇 개의 광고가 할당되었는지를 보여준다.
         */
        val query = """ 
             SELECT
                m.movie_code,
                m.movie_title,
                SUM(a.ad_price) as ad_sum_price,
                COUNT(*) as ad_count
             FROM
                 movie m,
                 movie_ad ma,
                 ad a
             WHERE 
                m.movie_code = ma.movie_code
                AND ma.AD_TITLE = a.AD_TITLE
             GROUP 
                BY m.movie_code , m.movie_title
             ORDER BY 
                SUM(a.ad_price) DESC
        """

        return JDBCManager
            .getInstance()
            .queryForMaps(query, arrayOf("m.movie_code", "m.movie_title", "ad_sum_price", "ad_count"))
    }

    // 영화 추가 메서드
    fun addMovie(movie: MovieDTO) {
        val query = """ 
            INSERT INTO movie 
            VALUES (?,?,?,?,?,?,?) 
        """

        val result = JDBCManager
            .getInstance()
            .update(query, arrayOf(
                movie.movieCode,
                movie.movieTitle,
                movie.movieDirector,
                movie.movieAge,
                movie.movieGenre,
                movie.movieStart,
                movie.movieEnd
            ))
    }
}