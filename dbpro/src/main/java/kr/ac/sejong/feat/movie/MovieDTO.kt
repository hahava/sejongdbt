package kr.ac.sejong.feat.movie

import kr.ac.sejong.config.ColumnName
import java.sql.Date

data class MovieDTO(
    @ColumnName("MOVIE_CODE")
    val movieCode: String? = null,

    @ColumnName("MOVIE_TITLE")
    val movieTitle: String? = null,

    @ColumnName("MOVIE_DIRECTOR")
    val movieDirector: String? = null,

    @ColumnName("MOVIE_AGE")
    val movieAge: Int = 0,

    @ColumnName("MOVIE_GENRE")
    val movieGenre: String? = null,

    @ColumnName("MOVIE_START")
    val movieStart: Date? = null,

    @ColumnName("MOVIE_END")
    val movieEnd: Date? = null
)