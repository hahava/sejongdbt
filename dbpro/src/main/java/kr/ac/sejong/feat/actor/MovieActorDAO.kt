package kr.ac.sejong.feat.actor

import kr.ac.sejong.config.JDBCManager

class MovieActorDAO private constructor() {
    companion object {
        @JvmStatic
        var instance: MovieActorDAO? = null
            get() {
                if (field == null) {
                    field = MovieActorDAO()
                }
                return field
            }
            private set
    }

    fun selectMovieActors(): List<MovieActorDTO> {
        val query = """
            SELECT
                ACTOR_CODE,
                MOVIE_CODE,
                ACTOR_ROLE
            FROM
                MOVIE_ACTOR
        """

        return JDBCManager
            .getInstance()
            .queryForList(query, MovieActorDTO::class.java)
    }
}