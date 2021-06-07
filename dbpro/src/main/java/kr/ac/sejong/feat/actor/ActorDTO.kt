package kr.ac.sejong.feat.actor

import kr.ac.sejong.config.ColumnName
import java.sql.Date

data class ActorDTO(
    @ColumnName("ACTOR_CODE")
    private val actorCode: String? = null,

    @ColumnName("ACTOR_NAME")
    private val actorName: String? = null,

    @ColumnName("ACTOR_GENDER")
    private val actorGender: String? = null,
    
    @ColumnName("ACTOR_BIRTH")
    private val actorBirth: Date? = null
)