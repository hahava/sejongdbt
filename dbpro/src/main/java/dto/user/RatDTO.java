package dto.user;

public class RatDTO implements DTO{
	public String MYUSER_ID;
	public String MOVIE_CODE;
	public int RAT_POINT;
	public String RAT_COMMENT;

	@Override
	public String toString() {
		return "RatDTO [MYUSER_ID=" + MYUSER_ID + ", MOVIE_CODE=" + MOVIE_CODE + ", RAT_POINT=" + RAT_POINT
				+ ", RAT_COMMENT=" + RAT_COMMENT + "]";
	}

	public RatDTO(String mYUSER_ID, String mOVIE_CODE, int rAT_POINT, String rAT_COMMENT) {
		super();
		MYUSER_ID = mYUSER_ID;
		MOVIE_CODE = mOVIE_CODE;
		RAT_POINT = rAT_POINT;
		RAT_COMMENT = rAT_COMMENT;
	}

}
