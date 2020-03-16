package dto.admin;

import java.sql.Date;

public class AdDTO implements DTO {
	public String AD_TITLE;
	public String AD_COMPANY;
	public Date AD_DATE;
	public int AD_PRICE;

	@Override
	public String toString() {
		return "AdDTO [AD_TITLE=" + AD_TITLE + ", AD_COMPANY=" + AD_COMPANY + ", AD_DATE=" + AD_DATE + ", AD_PRICE="
				+ AD_PRICE + "]";
	}

	public AdDTO(String aD_TITLE, String aD_COMPANY, Date aD_DATE, int aD_PRICE) {
		super();
		AD_TITLE = aD_TITLE;
		AD_COMPANY = aD_COMPANY;
		AD_DATE = aD_DATE;
		AD_PRICE = aD_PRICE;
	}

}
