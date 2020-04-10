package feat.advertisement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import config.ColumnName;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieAdDTO {

	@ColumnName("CNT")
	private int cnt;

	@ColumnName("MOVIE_CODE")
	private String movieCode;

	@ColumnName("AD_TITLE")
	private String adTitle;

}
