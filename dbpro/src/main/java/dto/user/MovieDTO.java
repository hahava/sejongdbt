package dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import oracle.connect.ColumnName;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieDTO implements DTO {
	@ColumnName("MOVIE_CODE")
	public String movieCode;

	@ColumnName("MOVIE_TITLE")
	public String movieTitle;

	@ColumnName("MOVIE_DIRECTOR")
	public String movieDirector;

	@ColumnName("MOVIE_AGE")
	public int movieAge;

	@ColumnName("MOVIE_GENRE")
	public String movieGenre;

	@ColumnName("MOVIE_START")
	public Date movieStart;

	@ColumnName("MOVIE_END")
	public Date movieEnd;
}
