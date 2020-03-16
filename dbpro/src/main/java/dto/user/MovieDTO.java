package dto.user;

import java.sql.Date;

public class MovieDTO implements DTO{
	public String MOVIE_CODE;
	public String MOVIE_TITLE;
	public String MOVIE_DIRECTOR;
	public int MOVIE_AGE;
	public String MOVIE_GENRE;
	public Date MOVIE_START;
	public Date MOVIE_END;

	@Override
	public String toString() {
		return "[MOVIE_CODE=" + MOVIE_CODE + ", MOVIE_TITLE=" + MOVIE_TITLE + ", MOVIE_DIRECTOR="
				+ MOVIE_DIRECTOR + ", MOVIE_AGE=" + MOVIE_AGE + ", MOVIE_GENRE=" + MOVIE_GENRE + ", MOVIE_START="
				+ MOVIE_START + ", MOVIE_END=" + MOVIE_END + "]";
	}

	public MovieDTO(String mOVIE_CODE, String mOVIE_TITLE, String mOVIE_DIRECTOR, int mOVIE_AGE, String mOVIE_GENRE,
			Date mOVIE_START, Date mOVIE_END) {
		MOVIE_CODE = mOVIE_CODE;
		MOVIE_TITLE = mOVIE_TITLE;
		MOVIE_DIRECTOR = mOVIE_DIRECTOR;
		MOVIE_AGE = mOVIE_AGE;
		MOVIE_GENRE = mOVIE_GENRE;
		MOVIE_START = mOVIE_START;
		MOVIE_END = mOVIE_END;
	}
	

	public MovieDTO() {
		// TODO Auto-generated constructor stub
	}

}
