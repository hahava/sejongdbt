package kr.ac.sejong.dao;

import kr.ac.sejong.feat.rat.RatDAO;
import org.junit.Test;

public class RatDAOTest {

	@Test
	public void getRatingsTest() {
		RatDAO.getInstance().selectRatings();
	}

	@Test
	public void getRatingByUserIdTest() {
		// given
		String userId = "hahava";

		// when
		RatDAO.getInstance().selectRatListByUserId(userId);
	}

	@Test
	public void getMovieRatingAndCommentTest() {
		// given
		String movieTitle = "겟 아웃";

		// when
		RatDAO.getInstance().selectMovieRat(movieTitle);
	}
}
