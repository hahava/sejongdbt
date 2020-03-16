package dto.user;

import java.sql.Date;

public class MoviePaymentDTO implements DTO {
	public int MOVIE_PAYMENT_CODE;
	public String MYUSER_ID;
	public String MOVIE_CODE;
	public String PAYMENT_CODE;
	public Date PAYMENT_DATE;

	public int getMOVIE_PAYMENT_CODE() {
		return MOVIE_PAYMENT_CODE;
	}

	public void setMOVIE_PAYMENT_CODE(int mOVIE_PAYMENT_CODE) {
		MOVIE_PAYMENT_CODE = mOVIE_PAYMENT_CODE;
	}

	public String getMYUSER_ID() {
		return MYUSER_ID;
	}

	public void setMYUSER_ID(String mYUSER_ID) {
		MYUSER_ID = mYUSER_ID;
	}

	public String getMOVIE_CODE() {
		return MOVIE_CODE;
	}

	public void setMOVIE_CODE(String mOVIE_CODE) {
		MOVIE_CODE = mOVIE_CODE;
	}

	public String getPAYMENT_CODE() {
		return PAYMENT_CODE;
	}

	public void setPAYMENT_CODE(String pAYMENT_CODE) {
		PAYMENT_CODE = pAYMENT_CODE;
	}

	public Date getPAYMENT_DATE() {
		return PAYMENT_DATE;
	}

	public void setPAYMENT_DATE(Date pAYMENT_DATE) {
		PAYMENT_DATE = pAYMENT_DATE;
	}

	@Override
	public String toString() {
		return "MoviePaymentDTO [MOVIE_PAYMENT_CODE=" + MOVIE_PAYMENT_CODE + ", MYUSER_ID=" + MYUSER_ID + ", MOVIE_CODE=" + MOVIE_CODE
				+ ", PAYMENT_CODE=" + PAYMENT_CODE + ", PAYMENT_DATE=" + PAYMENT_DATE + "]";
	}

	public MoviePaymentDTO() {

	}

	public MoviePaymentDTO(int mOVIE_PAYMENT_CODE, String mYUSER_ID, String mOVIE_CODE, String pAYMENT_CODE, Date pAYMENT_DATE) {
		super();
		MOVIE_PAYMENT_CODE = mOVIE_PAYMENT_CODE;
		MYUSER_ID = mYUSER_ID;
		MOVIE_CODE = mOVIE_CODE;
		PAYMENT_CODE = pAYMENT_CODE;
		PAYMENT_DATE = pAYMENT_DATE;
	}

}
