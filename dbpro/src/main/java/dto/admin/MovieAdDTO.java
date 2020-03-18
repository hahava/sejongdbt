package dto.admin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import oracle.connect.ColumnName;

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
