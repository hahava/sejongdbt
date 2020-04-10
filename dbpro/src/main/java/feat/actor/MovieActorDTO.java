package feat.actor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import oracle.connect.ColumnName;

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
