package kr.ac.sejong.feat.actor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import kr.ac.sejong.config.ColumnName;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieActorDTO {

	@ColumnName("ACTOR_CODE")
	private String actorCode;

	@ColumnName("MOVIE_CODE")
	private String movieCode;

	@ColumnName("ACTOR_ROLE")
	private String actorRole;

}
