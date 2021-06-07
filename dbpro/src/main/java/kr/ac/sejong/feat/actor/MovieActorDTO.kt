package kr.ac.sejong.feat.actor

import kr.ac.sejong.config.ColumnName

data class MovieActorDTO(
    @ColumnName("ACTOR_CODE")
    private val actorCode: String? = null,

    @ColumnName("MOVIE_CODE")
    private val movieCode: String? = null,

    @ColumnName("ACTOR_ROLE")
    private val actorRole: String? = null
)