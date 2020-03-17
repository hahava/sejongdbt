package dto.user;

import java.sql.Date;

public class MyuserDTO implements DTO {
	public String MYUSER_ID;
	public String MYUSER_NAME;
	public String MYUSER_PW;
	public Date MYUSER_BIRTH;
	public String MYUSER_PHONE;
	public String MYUSER_EMAIL;

	@Override
	public String toString() {
		return "MyuserDTO [MYUSER_ID=" + MYUSER_ID + ", MYUSER_NAME=" + MYUSER_NAME + ", MYUSER_PW=" + MYUSER_PW
				+ ", MYUSER_BIRTH=" + MYUSER_BIRTH + ", MYUSER_PHONE=" + MYUSER_PHONE + ", MYUSER_EMAIL=" + MYUSER_EMAIL
				+ "]";
	}

	public String getMYUSER_ID() {
		return MYUSER_ID;
	}

	public void setMYUSER_ID(String mYUSER_ID) {
		MYUSER_ID = mYUSER_ID;
	}

	public String getMYUSER_NAME() {
		return MYUSER_NAME;
	}

	public void setMYUSER_NAME(String mYUSER_NAME) {
		MYUSER_NAME = mYUSER_NAME;
	}

	public String getMYUSER_PW() {
		return MYUSER_PW;
	}

	public void setMYUSER_PW(String mYUSER_PW) {
		MYUSER_PW = mYUSER_PW;
	}

	public Date getMYUSER_BIRTH() {
		return MYUSER_BIRTH;
	}

	public void setMYUSER_BIRTH(Date mYUSER_BIRTH) {
		MYUSER_BIRTH = mYUSER_BIRTH;
	}

	public String getMYUSER_PHONE() {
		return MYUSER_PHONE;
	}

	public void setMYUSER_PHONE(String mYUSER_PHONE) {
		MYUSER_PHONE = mYUSER_PHONE;
	}

	public String getMYUSER_EMAIL() {
		return MYUSER_EMAIL;
	}

	public void setMYUSER_EMAIL(String mYUSER_EMAIL) {
		MYUSER_EMAIL = mYUSER_EMAIL;
	}

	public MyuserDTO(String mYUSER_ID, String mYUSER_NAME, String mYUSER_PW, Date mYUSER_BIRTH, String mYUSER_PHONE,
			String mYUSER_EMAIL) {
		super();
		MYUSER_ID = mYUSER_ID;
		MYUSER_NAME = mYUSER_NAME;
		MYUSER_PW = mYUSER_PW;
		MYUSER_BIRTH = mYUSER_BIRTH;
		MYUSER_PHONE = mYUSER_PHONE;
		MYUSER_EMAIL = mYUSER_EMAIL;
	}

	public MyuserDTO() {

	}

}
