package kr.ac.sejong.feat.movie;

import kr.ac.sejong.auth.Account;
import kr.ac.sejong.auth.AuthLevel;
import kr.ac.sejong.feat.actor.ActorDAO;
import kr.ac.sejong.menu.MenuMapper;
import kr.ac.sejong.menu.MenuMapping;
import kr.ac.sejong.menu.MenuSelector;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.sql.Date;
import java.util.List;
import java.util.Map;

import static kr.ac.sejong.util.ConsoleUtil.*;

@MenuMapper
public class MovieMapper {

	@MenuMapping("영화 정보")
	public void showMovieMenu() throws IOException {
		while (true) {
			System.out.println("영화 관련 정보 조회 메뉴입니다.");
			System.out.println("0. 현재 영화 정보 보기");
			System.out.println("1. 영화 개요 검색");
			System.out.println("2. 배우기반 영화 검색");
			System.out.println("3. 특정 영화에 출연한 배우 검색");
			// 관리자 여부 화인
			if (Account.getUser().getLevel().equals(AuthLevel.ADMIN)) {
				System.out.println("4. 영화 추가 하기");
				System.out.println("5. 영화 수정 하기");
				System.out.println("6. 영화 삭제 하기");
			}
			System.out.println("7. 뒤로");

			String userInput = readString();

			if (StringUtils.equals(userInput, "뒤로")) {
				break;
			}

			MenuSelector.getInstance().select(userInput);
		}
	}

	@MenuMapping("영화 통계")
	public void getMovieStatics() throws IOException {
		System.out.println("관리자용 영화 상세 통계 메뉴입니다.");
		while (true) {
			printLn("1. 영화 별 평점 통계");
			printLn("2. 영화별 광고비 합계");
			printLn("3. 뒤로");

			String requestMenu = readString("영화 별 평점 통계");

			if (StringUtils.equals("뒤로", requestMenu)) {
				return;
			}
			MenuSelector.getInstance().select(requestMenu);
		}

		//		switch (kr.ac.sejong.menu) {
		//			case 1:
		//				// 영화별 평점 통계를 내는 메서드이다.
		//				getMovieRatStatic(0);
		//				break;
		//			case 2:
		//				// 영화에 할당된 광고비의 합계 통계를 내는 메서드이다.
		//				getMovieADStatic();
		//				break;
		//			case 3:
		//				return;
		//			default:
		//				System.out.println("잘못 입력하셨습니다.");
		//				break;
		//
		//		}
	}

	@MenuMapping("현재 영화 정보 보기")
	public void getMovies() {
		List<MovieDTO> movies = MovieDAO.getInstance().selectMovies();
		movies.forEach(movieDTO -> System.out.println(movieDTO.toString()));
	}

	@MenuMapping("영화 개요 검색")
	public void getMovieWithActors() throws IOException {
		String movieTitle = readString("영화 정보를 출력합니다. 조회를 원하는 영화이름을 입력하세요.(* : 배우와 모두 출력)");
		List<Map<String, Object>> result = MovieDAO.getInstance().selectMoviesWithActors(movieTitle);
		for (Map<String, Object> row : result) {
			System.out.print("[ ");
			row.forEach((s, o) -> System.out.print(s + " : " + o + ", \t"));
			System.out.println("]");
		}
	}

	@MenuMapping("배우기반 영화 검색")
	public void getMoviesByActor() throws IOException {
		String actorName = readString("특정 배우가 출연한 영화를 검색합니다. 배우 이름을 입력하세요.");
		MovieDAO
			.getInstance()
			.selectMoviesByActor(actorName)
			.forEach(stringObjectMap -> {
				System.out.print("[ ");
				stringObjectMap.forEach((s, o) -> System.out.print(s + ":" + o + "\t"));
				System.out.println(" ]");
			});
	}

	@MenuMapping("특정 영화에 출연한 배우 검색")
	public void getActorByMovieTitle() throws IOException {
		String actorName = readString("배우명을 입력해주세요");
		ActorDAO
			.getInstance()
			.selectActorByMovieTitle(actorName)
			.forEach(actorDTO -> System.out.println(actorDTO.toString()));
	}

	@MenuMapping(value = "영화 추가 하기", onlyForAdmin = true)
	public void addMovie() throws IOException {
		printLn("영화를 추가합니다");
		printLn("현재 상영중인 영화");
		printLn(HORIZONTAL_RULE);

		MovieDAO.getInstance().selectMovies().forEach(movieDTO -> movieDTO.toString());

		MovieDTO newMovie = new MovieDTO();
		newMovie.setMovieCode(readString("영화 코드 : "));
		newMovie.setMovieTitle(readString("영화 제목 : "));
		newMovie.setMovieDirector(readString("영화 감독 :"));
		newMovie.setMovieAge(readInt("연령 :"));
		newMovie.setMovieGenre(readString("장르 : "));
		newMovie.setMovieStart(Date.valueOf(readString("상영 시작 일자 (yyyy-mm-dd): ")));
		newMovie.setMovieEnd(Date.valueOf(readString("상영 종료 일자(yyyy-mm-dd): ")));

		MovieDAO.getInstance().addMovie(newMovie);
	}

	@MenuMapping(value = "영화 수정 하기", onlyForAdmin = true)
	public void modifyMovie() throws IOException {
		printLn("현재 영화 목록");
		List<MovieDTO> movies = MovieDAO.getInstance().selectMovies();
		movies.forEach(movieDTO -> movieDTO.toString());
		printLn(HORIZONTAL_RULE);
		String movieCode = readString("수정할 영화의 코드를 입력하세요: ");
		// TODO: 방어 코드 작성해야함
		MovieDTO movieDTO = new MovieDTO();
		movieDTO.setMovieCode(movieCode);
		movieDTO.setMovieTitle(readString("영화 제목: "));
		movieDTO.setMovieAge(readInt("연령 : "));
		movieDTO.setMovieGenre(readString("장르 : "));
		movieDTO.setMovieStart(Date.valueOf(readString("상영 시작 일자 (yyyy-mm-dd): ")));
		movieDTO.setMovieEnd(Date.valueOf(readString("상영 종료 일자(yyyy-mm-dd): ")));

		MovieDAO.getInstance().updateMovie(movieDTO);
	}

	@MenuMapping(value = "영화 삭제 하기", onlyForAdmin = true)
	public void removeMovie() throws IOException {
		printLn("현재 영화 목록");
		List<MovieDTO> movies = MovieDAO.getInstance().selectMovies();
		movies.forEach(movieDTO -> movieDTO.toString());
		printLn(HORIZONTAL_RULE);
		// TODO: 방어 코드 작성 해야함
		String movieCode = readString("지울 영화 코드 입력");
		MovieDAO.getInstance().deleteMovie(movieCode);
	}

	@MenuMapping(value = "영화 별 평점 통계", onlyForAdmin = true)
	public void getMovieRatStatic() throws IOException {
		printLn("영화별 평점 통계입니다.");
		double minScore = readInt("최소 범위를 입력해주세요.(0~5)(0 입력시, 전체 출력)");
		List<Map<String, Object>> movies = MovieDAO.getInstance().selectMovieRatStatic(minScore);
		movies.forEach(stringObjectMap -> {
			stringObjectMap.forEach((s, o) -> {
				System.out.print(s + " : " + o);
			});
			System.out.println("\t");
		});
	}

	@MenuMapping(value = "영화별 광고비 합계", onlyForAdmin = true)
	public void getMovieADStatic() throws IOException {
		printLn("영화별 광고비 합계. 영화에 할당된 광고료가 높은 순서로 출력됩니다.");
		List<Map<String, Object>> results = MovieDAO.getInstance().selectMovieADStatic();
		results.forEach(stringObjectMap -> {
			stringObjectMap.forEach((key, value) -> {
				System.out.print(key + " : " + value);
			});
			System.out.println();
		});
	}
}
