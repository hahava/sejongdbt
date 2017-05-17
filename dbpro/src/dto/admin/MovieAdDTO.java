package dto.admin;

public class MovieAdDTO implements DTO {
	public int CNT;
	public String MOVIE_CODE;
	public String AD_TITLE;

	@Override
	public String toString() {
		return "MovieAdDTO [CNT=" + CNT + ", MOVIE_CODE=" + MOVIE_CODE + ", AD_TITLE=" + AD_TITLE + "]";
	}

	public MovieAdDTO(int cNT, String mOVIE_CODE, String aD_TITLE) {
		super();
		CNT = cNT;
		MOVIE_CODE = mOVIE_CODE;
		AD_TITLE = aD_TITLE;
	}

}
