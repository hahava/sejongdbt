package feat.advertisement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import oracle.connect.ColumnName;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdDTO {
	@ColumnName("AD_TITLE")
	private String adTitle;

	@ColumnName("AD_COMPANY")
	private String adCompany;

	@ColumnName("AD_DATE")
	private Date adDate;

	@ColumnName("AD_PRICE")
	private int adPrice;
}
