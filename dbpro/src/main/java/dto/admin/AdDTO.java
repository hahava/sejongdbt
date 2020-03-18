package dto.admin;

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
	public String adTitle;

	@ColumnName("AD_COMPANY")
	public String adCompany;

	@ColumnName("AD_DATE")
	public Date adDate;

	@ColumnName("AD_PRICE")
	public int adPrice;
}
