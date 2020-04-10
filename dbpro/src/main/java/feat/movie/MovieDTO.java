package feat.movie;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import oracle.connect.ColumnName;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieDTO {
	@ColumnName("MOVIE_CODE")
	private String movieCode;

	@ColumnName("MOVIE_TITLE")
	private String movieTitle;

	@ColumnName("MOVIE_DIRECTOR")
	private String movieDirector;

	@ColumnName("MOVIE_AGE")
	private int movieAge;

	@ColumnName("MOVIE_GENRE")
	private String movieGenre;

	@ColumnName("MOVIE_START")
	private Date movieStart;

	@ColumnName("MOVIE_END")
	private Date movieEnd;
}
