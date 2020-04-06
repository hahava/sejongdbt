package feat.movie;

import oracle.connect.JDBCManager;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class MovieDAO {

	private static MovieDAO instance;

	private MovieDAO() {
	}

	public static MovieDAO getInstance() {
		if (instance == null) {
			instance = new MovieDAO();
		}
		return instance;
	}

	// Movie 테이블의 내용을 전부 출력한다.
	public List<MovieDTO> selectMovies() {
		final String query = "SELECT " +
			"	MOVIE_CODE, " +
			"	MOVIE_TITLE, " +
			"	MOVIE_DIRECTOR, " +
			"	MOVIE_AGE, " +
			"	MOVIE_GENRE, " +
			"	MOVIE_START, " +
			"	MOVIE_END " +
			"FROM " +
			"	MOVIE";
		return JDBCManager
			.getInstance()
			.queryForList(query, MovieDTO.class);
	}

	public int deleteMovie(String movieCode) {
		final String query = "DELETE FROM MOVIE WHERE MOVIE_CODE = ?";

		return JDBCManager
			.getInstance()
			.delete(query, new String[] {movieCode});
	}

	// 영화 내용 변경하기
	public void updateMovie(MovieDTO movie) {

		// 해당 where movie_code를 입력받아 수정한다.
		final String query = "UPDATE MOVIE" +
			" SET MOVIE_TITLE = ?, " +
			"	MOVIE_DIRECTOR = ?, " +
			"	MOVIE_AGE= ?, " +
			"	MOVIE_GENRE= ?, " +
			"	MOVIE_START=?, " +
			"	MOVIE_END=? " +
			"WHERE " +
			"	MOVIE_CODE =?";

		JDBCManager
			.getInstance()
			.update(query, new Object[] {
				movie.getMovieTitle(),
				movie.getMovieDirector(),
				movie.getMovieAge(),
				movie.getMovieGenre(),
				movie.getMovieStart(),
				movie.getMovieEnd(),
				movie.getMovieCode()
			});
	}

	// 영화 정보 보여주기 - 영화 개요 검색
	public List<Map<String, Object>> selectMoviesWithActors(String movieTitle) {

		StringBuilder query = new StringBuilder("SELECT ")
			.append("M.MOVIE_CODE, ")
			.append("MOVIE_TITLE, ")
			.append("MOVIE_DIRECTOR, ")
			.append("MOVIE_AGE, ")
			.append("MOVIE_GENRE, ")
			.append("MOVIE_START, ")
			.append("MOVIE_END, ")
			.append("A.ACTOR_NAME, ")
			.append("MA.ACTOR_ROLE, ")
			.append("A.ACTOR_BIRTH, ")
			.append("A.ACTOR_GENDER ")
			.append("FROM ")
			.append("MOVIE M, MOVIE_ACTOR MA, ACTOR A ")
			.append("WHERE ");

		if (StringUtils.equals(movieTitle, "*") == false) {
			query.append("MOVIE_TITLE = ")
				.append("'")
				.append(movieTitle)
				.append("' AND ");
		}

		query.append("MA.MOVIE_CODE = M.MOVIE_CODE ")
			.append("AND ")
			.append("MA.ACTOR_CODE = A.ACTOR_CODE ")
			.append("ORDER BY M.MOVIE_CODE ");

		return JDBCManager
			.getInstance()
			.queryForMaps(query.toString(),
				new String[] {"M.MOVIE_CODE", "MOVIE_TITLE", "MOVIE_DIRECTOR", "MOVIE_AGE", "MOVIE_GENRE",
					"MOVIE_START", "MOVIE_END", "A.ACTOR_NAME", "MA.ACTOR_ROLE", "A.ACTOR_BIRTH", "A.ACTOR_GENDER"});

	}

	// 특정 배우가 출연한 영화 검색
	public List<Map<String, Object>> selectMoviesByActor(String actorName) {

		String query = "SELECT " +
			"    mv.movie_title, " +
			"    ac.actor_name, " +
			"    ac.actor_birth, " +
			"    ac.actor_gender, " +
			"    ma.actor_role " +
			"FROM " +
			"	movie mv " +
			"JOIN " +
			"	movie_actor ma ON mv.movie_code = ma.movie_code " +
			"JOIN " +
			"	actor ac ON ac.actor_code = ma.actor_code " +
			"WHERE ac.actor_name = '" + actorName + "'";

		return JDBCManager
			.getInstance()
			.queryForMaps(query,
				new String[] {"mv.movie_title", "ac.actor_name", "ac.actor_birth", "ac.actor_gender", "ma.actor_role"});
	}

	// 영화 예약 횟수가 가장 많은 회원의 id와 예약횟수를 출력한다.
	public void getPersonWhoBookedTheMostMovies() {

		final String query = "SELECT " +
			"	m.myuser_id, COUNT(*) AS total_count " +
			"FROM " +
			"	myuser m " +
			"JOIN " +
			"	movie_payment mp ON m.myuser_id = mp.myuser_id " +
			"GROUP BY myuser_id " +
			"ORDER BY total_count DESC " +
			"LIMIT 1;";

		JDBCManager
			.getInstance()
			.queryForMap(query, new String[] {"m.myuser_id", "total_count"})
			.forEach((key, value) -> System.out.println(key + ":" + value));
	}

	// 특정 횟수를 입력하고 그 횟수보다 더 많이 예약한 회원의 id와 예약횟수를 출력한다.
	public void getMovieReservationCountOfPerson(int reservationCount) {
		System.out.println("범위를 지정하세요.입력한 숫자 이상 영화를 본 회원들을 출력합니다.");
		final String query = "SELECT " +
			"	m.myuser_id as userId, " +
			"	COUNT(*) as watchCount " +
			"FROM " +
			"	myuser m," +
			"	movie_payment mp " +
			"WHERE " +
			"	m.myuser_id = mp.myuser_id " +
			"GROUP BY m.myuser_id " +
			"HAVING COUNT(*) >=  " + reservationCount;

		JDBCManager.getInstance().queryForMaps(query, new String[] {"userId", "watchCount"})
			.forEach(stringObjectMap -> {
				System.out.print("[ ");
				stringObjectMap.forEach((key, value) -> {
					System.out.print(key + " : " + value + "\t");
				});
				System.out.println(" ]");
			});
	}

	// 영화 별 평점통계와 광고비 통계를 낼 수 있는 메뉴이다.
	public void movieStaticsMenu() {

		int menu = 0;

		Scanner sc = new Scanner(System.in);
		System.out.println("관리자용 영화 상세 통계 메뉴입니다.");
		System.out.println("1. 영화 별 평점 통계");
		System.out.println("2. 영화별 광고비 합계");
		System.out.println("3. 뒤로");

		menu = sc.nextInt();

		switch (menu) {
			case 1:
				// 영화별 평점 통계를 내는 메서드이다.
				selectMovieRatStatic(0);
				break;
			case 2:
				// 영화에 할당된 광고비의 합계 통계를 내는 메서드이다.
				selectMovieADStatic();
				break;
			case 3:
				return;
			default:
				System.out.println("잘못 입력하셨습니다.");
				break;

		}
	}

	// 영화별 평점 통계를 내는 메서드이다.
	public List<Map<String, Object>> selectMovieRatStatic(double score) {

		System.out.println("영화별 평점 통계입니다.");
		System.out.println("최소 범위를 입력해주세요.(0~5)(0 입력시, 전체 출력)");

		/*
		 * 관리자가 최소 평점을 입력하면, 해당 평점 이상이 되는 영화의 movie_code, movie_title, 평점, 점수를 준
		 * 사람들의 수를 출력한다. group by와 having이 사용되었다.
		 */
		final String query = "SELECT " +
			"	m.movie_code, " +
			"	m.movie_title, " +
			"	AVG(r.rat_point), " +
			"	COUNT(m.movie_code) " +
			"FROM " +
			"movie m " +
			"JOIN " +
			"	rat r ON m.movie_code = r.movie_code " +
			"GROUP BY m.movie_code , m.movie_title " +
			"having avg(r.rat_point)>=" + score;

		return JDBCManager.getInstance().queryForMaps(query,
			new String[] {"m.movie_code", "m.movie_title", "AVG(r.rat_point)", "COUNT(m.movie_code)"});
	}

	// 영화에 할당된 광고비의 합계 통계를 내는 메서드이다.
	public List<Map<String, Object>> selectMovieADStatic() {
		/*
		 * movie와 movie_ad 테이블을 조인한 뒤 movie_code, movie_title로 grouping을 하고, 광고비 합계가 가장 많은 순서대로 정렬했다.
		 * movie_code, movie_title, 광고비 합계, 해당 영화에 몇 개의 광고가 할당되었는지를 보여준다.
		 * */
		final String query = "SELECT " +
			"	m.movie_code, " +
			"	m.movie_title, " +
			"	SUM(a.ad_price) as ad_sum_price, " +
			"	COUNT(*) as ad_count " +
			"FROM " +
			"    movie m, " +
			"    movie_ad ma, " +
			"    ad a " +
			"WHERE " +
			"	m.movie_code = ma.movie_code " +
			"	AND ma.AD_TITLE = a.AD_TITLE " +
			"GROUP BY m.movie_code , m.movie_title " +
			"ORDER BY SUM(a.ad_price) DESC;";

		return JDBCManager
			.getInstance()
			.queryForMaps(query, new String[] {"m.movie_code", "m.movie_title", "ad_sum_price", "ad_count"});

	}

	// 영화 추가 메서드
	public void addMovie(MovieDTO movie) {
		final String query = "insert into movie values (?,?,?,?,?,?,?)";
		int result = JDBCManager
			.getInstance()
			.update(query, new Object[] {
				movie.getMovieCode(),
				movie.getMovieTitle(),
				movie.getMovieDirector(),
				movie.getMovieAge(),
				movie.getMovieGenre(),
				movie.getMovieStart(),
				movie.getMovieEnd()
			});
	}

}
