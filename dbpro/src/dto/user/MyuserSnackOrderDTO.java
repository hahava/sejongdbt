package dto.user;

import java.sql.Date;

public class MyuserSnackOrderDTO implements DTO {
	public int ORDER_NUM;
	public String MYUSER_ID;
	public String SNACK_CODE;
	public Date ORDER_DATE;

	public int getORDER_NUM() {
		return ORDER_NUM;
	}

	public void setORDER_NUM(int oRDER_NUM) {
		ORDER_NUM = oRDER_NUM;
	}

	public String getMYUSER_ID() {
		return MYUSER_ID;
	}

	public void setMYUSER_ID(String mYUSER_ID) {
		MYUSER_ID = mYUSER_ID;
	}

	public String getSNACK_CODE() {
		return SNACK_CODE;
	}

	public void setSNACK_CODE(String sNACK_CODE) {
		SNACK_CODE = sNACK_CODE;
	}

	public Date getORDER_DATE() {
		return ORDER_DATE;
	}

	public void setORDER_DATE(Date oRDER_DATE) {
		ORDER_DATE = oRDER_DATE;
	}

	@Override
	public String toString() {
		return "MyuserSnackOrder [ORDER_NUM=" + ORDER_NUM + ", MYUSER_ID=" + MYUSER_ID + ", SNACK_CODE=" + SNACK_CODE
				+ ", ORDER_DATE=" + ORDER_DATE + "]";
	}

	public MyuserSnackOrderDTO(int oRDER_NUM, String mYUSER_ID, String sNACK_CODE, Date oRDER_DATE) {
		ORDER_NUM = oRDER_NUM;
		MYUSER_ID = mYUSER_ID;
		SNACK_CODE = sNACK_CODE;
		ORDER_DATE = oRDER_DATE;
	}

}
