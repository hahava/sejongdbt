package dao.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import dto.user.RatDTO;
import main.ExecuteProject;
import oracle.connect.OracleJDBCManager;

public class RatDAO implements DAO {

	private static RatDAO instance = new RatDAO();

	private RatDAO() {

	}

	public static RatDAO getInstance() {
		return instance;
	}

	private Connection getConnection() {
		OracleJDBCManager manager = new OracleJDBCManager();
		String oracleId = "s15010924";
		String passwd = "s15010924";
		int port = 1521;
		Connection conn;
		manager.registerOracleJDBCDriver();
		conn = manager.connect(oracleId, passwd, port);

		return conn;
	}

	public void list() {

		ArrayList<RatDTO> arrayList = new ArrayList<>();
		Connection conn = getConnection();
		PreparedStatement pstm = null;
		ResultSet result = null;
		String query = "select MYUSER_ID, MOVIE_CODE, RAT_POINT,RAT_COMMENT  from RAT";

		try {
			pstm = conn.prepareStatement(query);
			result = pstm.executeQuery();
			while (result.next()) {
				arrayList.add(new RatDTO(result.getString("MYUSER_ID"), result.getString("MOVIE_CODE"), result.getInt("RAT_POINT"),
						result.getString("RAT_COMMENT")));
			}
		} catch (SQLException e1) {
			System.out.println(e1);
		}

		try {
			result.close();
			pstm.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < arrayList.size(); i++) {
			System.out.println(arrayList.get(i).toString());
		}
	}

	// 로그인한 유저의 통계 내역을 출력한다.
	public void listMe(String id) {

		Connection conn = getConnection();
		PreparedStatement pstm = null;
		ResultSet result = null;
		/*
		 * movie, rat 테이블을 조인(movie_code)하고 로그인한 유저가 매긴 평점과 감상평을 출력해준다.
		 */
		String query = "select r.myuser_id,m.movie_code,m.movie_title, r.rat_point,r.rat_comment " + "from movie m, rat r "
				+ "where m.movie_code=r.movie_code and r.myuser_id=?";

		try {
			pstm = conn.prepareStatement(query);
			pstm.setString(1, ExecuteProject.id);
			result = pstm.executeQuery();
			System.out.println("아이디\t영화코드\t영화제목\t\t평점\t한줄평");
			while (result.next()) {
				System.out.println(result.getString(1) + "\t" + result.getString(2) + "\t" + result.getString(3) + "\t\t" + result.getInt(4) + "\t"
						+ result.getString(5));
			}
		} catch (SQLException e1) {
			System.out.println(e1);
		}

		try {
			result.close();
			pstm.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	// 해당 영화의 평점의 평균과 감상평을 보여주는 메서드이다.
	public void showMovieRat() {

		String movieName = null;

		Connection conn = getConnection();
		PreparedStatement pstm = null;
		ResultSet result = null;

		Scanner sc = new Scanner(System.in);

		System.out.println("원하시는 영화의 평점의 평균과 감상평을 보여드립니다.");
		System.out.println("영화 이름을 입력하세요.");

		movieName = sc.nextLine();
		/*
		 * subquery를 이용해서 movie table에서 movie_code를 얻은 뒤 rat 테이블에서 movie_code로 그룹핑해 평균과 평점을 매긴 회원 수를 출력한다.
		 * 
		 * */
		String query1 = "select avg(rat_point),count(*) from rat where movie_code in" + " (select movie_code from movie where movie_title=?) "
				+ "group by movie_code";
		/*
		 * 해당영화를 본 회원들의 아이디, 평점, 감상평을 출력한다.
		 * subquery를 이용했다.
		 * */
		String query2 = "select myuser_id, rat_point, rat_comment "
				+ "from rat where movie_code in(select movie_code from movie where movie_title=?)";

		try {
			pstm = conn.prepareStatement(query1);
			pstm.setString(1, movieName);
			result = pstm.executeQuery();
			
			System.out.println("평점평균\t참여수");
			
			if (result.next()) {
				System.out.println(result.getDouble(1) + "\t" + result.getInt(2));
			}
			
			pstm = conn.prepareStatement(query2);
			pstm.setString(1, movieName);
			
			System.out.println("아이디\t평점\t코멘트");
			
			result = pstm.executeQuery();
			while (result.next()) {
				System.out.println(result.getString(1) + "\t" + result.getDouble(2) + "\t" + result.getString(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			result.close();
			pstm.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
