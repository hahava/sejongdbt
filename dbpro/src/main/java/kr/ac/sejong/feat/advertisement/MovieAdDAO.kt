package kr.ac.sejong.feat.advertisement

import kr.ac.sejong.config.JDBCManager

class MovieAdDAO private constructor() {
    companion object {
        @JvmStatic
        var instance: MovieAdDAO? = null
            get() {
                if (field == null) {
                    field = MovieAdDAO()
                }
                return field
            }
            private set
    }

    fun selectMovieADs(): List<MovieAdDTO> {
        val query = """
            SELECT
                CNT,
                MOVIE_CODE,
                AD_TITLE
            FROM
                MOVIE_AD
        """

        return JDBCManager
            .getInstance()
            .queryForList(query, MovieAdDTO::class.java)
    }
}