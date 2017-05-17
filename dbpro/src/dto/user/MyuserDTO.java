package dto.user;

import java.sql.Date;

public class MyuserDTO implements DTO{
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

}
