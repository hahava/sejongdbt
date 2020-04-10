package feat.actor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import oracle.connect.ColumnName;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActorDTO {
	@ColumnName("ACTOR_CODE")
	private String actorCode;

	@ColumnName("ACTOR_NAME")
	private String actorName;

	@ColumnName("ACTOR_GENDER")
	private String actorGender;

	@ColumnName("ACTOR_BIRTH")
	private Date actorBirth;
}
