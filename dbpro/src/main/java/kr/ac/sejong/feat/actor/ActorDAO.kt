package kr.ac.sejong.feat.actor

import kr.ac.sejong.config.JDBCManager

class ActorDAO private constructor() {
    companion object {
        @JvmStatic
        var instance: ActorDAO? = null
            get() {
                if (field == null) {
                    field = ActorDAO()
                }
                return field
            }
            private set
    }

    // 등록된 배우를 전부 출력한다.
    fun selectActors(): List<ActorDTO> {
        val query = """ 
             SELECT 
                ACTOR_CODE,
                ACTOR_NAME,
                ACTOR_GENDER,
                ACTOR_BIRTH
             FROM 
                ACTOR
        """

        return JDBCManager
            .getInstance()
            .queryForList(query, ActorDTO::class.java)
    }

    // 특정 영화에 출연한 배우 검색
    fun selectActorByMovieTitle(movieTitle: String): List<ActorDTO> {
        val query = """
            SELECT 
                AC.ACTOR_CODE AS ACTOR_CODE, 
                AC.ACTOR_NAME AS ACTOR_NAME, 
                AC.ACTOR_BIRTH AS ACTOR_BIRTH, 
                AC.ACTOR_GENDER AS ACTOR_GENDER
            FROM 
                MOVIE_ACTOR MA
            JOIN
                MOVIE MV ON MV.MOVIE_CODE = MA.MOVIE_CODE
            JOIN
                ACTOR AC ON AC.ACTOR_CODE = MA.ACTOR_CODE
            WHERE 
                MOVIE_TITLE = $movieTitle
        """

        return JDBCManager
            .getInstance()
            .queryForList(query, ActorDTO::class.java)
    }
}