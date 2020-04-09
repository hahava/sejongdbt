package feat.rat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import oracle.connect.ColumnName;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RatDTO {
	@ColumnName("MYUSER_ID")
	private String myuserId;

	@ColumnName("MOVIE_CODE")
	private String movieCode;

	@ColumnName("RAT_POINT")
	private int ratPoint;

	@ColumnName("RAT_COMMENT")
	private String ratComment;
}
