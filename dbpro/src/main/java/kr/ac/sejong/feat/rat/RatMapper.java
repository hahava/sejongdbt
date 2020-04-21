package kr.ac.sejong.feat.rat;

import kr.ac.sejong.feat.movie.MovieDAO;
import kr.ac.sejong.menu.MenuMapper;
import kr.ac.sejong.menu.MenuMapping;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static kr.ac.sejong.util.ConsoleUtil.printLn;
import static kr.ac.sejong.util.ConsoleUtil.readString;

@MenuMapper
public class RatMapper {

	@MenuMapping("회원평점정보")
	public void getRatings() {
		RatDAO
			.getInstance()
			.selectRatings()
			.forEach(ratDTO -> System.out.println(ratDTO.toString()));
	}

	@MenuMapping("나의 평점 정보")
	public void getMyRating() {
		//	TODO: Accout 수정 후 변경할 것
		List<Map<String, Object>> results = RatDAO.getInstance().selectRatListByUserId("id");
		results.forEach(stringObjectMap -> {
			stringObjectMap.forEach((key, value) -> System.out.print(key + ":" + value + "\t"));
			System.out.println();
		});
	}

	@MenuMapping("영화 평점 정보")
	public void getMovieRating() throws IOException {
		printLn("원하시는 영화의 평점의 평균과 감상평을 보여드립니다.");
		MovieDAO.getInstance().selectMovies().forEach(movieDTO -> System.out.println(movieDTO.toString()));
		String movieTitle = readString("영화 이름을 입력하세요.");
		List<RatDTO> results = RatDAO.getInstance().selectMovieRat(movieTitle);
		results.forEach(ratDTO -> System.out.println(ratDTO.toString()));
	}

}
