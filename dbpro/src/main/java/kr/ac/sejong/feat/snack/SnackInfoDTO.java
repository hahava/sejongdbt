package kr.ac.sejong.feat.snack;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import kr.ac.sejong.config.ColumnName;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SnackInfoDTO {
	@ColumnName("SNACK_CODE")
	private String snackCode;

	@ColumnName("SNACK_NAME")
	private String snackName;

	@ColumnName("SNACK_CONTENT")
	private String snackContent;

	@ColumnName("SNACK_CAL")
	private int snackCal;

	@ColumnName("SNACK_PRICE")
	private int snackPrice;
}
