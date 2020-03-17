package dao.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import dto.admin.ActorDTO;
import oracle.connect.*;

public class ActorDAO implements DAO {

	// driver를 이용하여 서버에 접속하는 메서드
	private Connection getConnection() {
		JDBCManager manager = new JDBCManager();
		String oracleId = "s15010924";
		String passwd = "s15010924";
		int port = 1521;

		Connection conn = manager.connect(oracleId, passwd, port);
		return conn;
	}

	private ActorDAO() {
	}

	// 객체를 생성한다.
	private static ActorDAO instance = new ActorDAO();

	// 객체를 반환하다.
	public static ActorDAO getInstance() {
		return instance;
	}

	// 등록된 배우를 전부 출력한다.
	public void list() {

		ArrayList<ActorDTO> actor = new ArrayList<>();

		Connection conn = getConnection();
		PreparedStatement pstm = null;
		ResultSet result = null;

		String query = "select actor_code,actor_name,actor_gender, actor_birth from ACTOR";

		try {
			pstm = conn.prepareStatement(query);
			result = pstm.executeQuery();
			while (result.next()) {
				actor.add(new ActorDTO(result.getString("actor_code"), result.getString("actor_name"), result.getString("actor_gender"),
						result.getString("actor_birth")));
			}
		} catch (SQLException e1) {
			System.out.println(e1);
		}

		try {
			result.close();
			pstm.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i = 0; i < actor.size(); i++) {
			System.out.println(actor.get(i).toString());
		}
	}

	// 특정 영화에 출연한 배우 검색
	public void actorSearchByMovie() {

		Scanner sc = new Scanner(System.in);

		Connection conn = getConnection();
		PreparedStatement pstm = null;
		ResultSet result = null;

		String movieName;
		ArrayList<ActorDTO> actorByMovie = new ArrayList<ActorDTO>();

		System.out.println("특정 영화에 출연한 배우를 검색합니다. 영화 명을 입력하세요.");

		movieName = sc.nextLine();

		/*
		 * 영화 이름으로 검색할 것이기 때문에 movie 테이블에서 해당 영화이름을 가진 영화의 코드를 받아온 뒤, movie_actor 테이블에서 해당 영화코드에 할당된 actor_code를 
		 * 받아오고, 그것을 기반으로 actor테이블에서 배우 정보를 얻어온다.
		 * subquery를 이용했다.
		 * */
		String query = "select actor_code, actor_name,actor_gender, actor_birth from actor where actor_code in "
				+ "(select actor_code from movie_actor where movie_code in " + "(select movie_code from movie where movie_title='" + movieName
				+ "'))";

		try {
			pstm = conn.prepareStatement(query);
			result = pstm.executeQuery();
			while (result.next()) {
				actorByMovie.add(new ActorDTO(result.getString("actor_code"), result.getString("actor_name"), result.getString("actor_gender"),
						result.getString("actor_birth")));
			}
			if (actorByMovie.size() == 0) {
				System.out.println("그런 영화가 없거나 관리자가 배우정보를 등록하지 않았습니다.");
				return;
			}
			System.out.println("배우이름\t배우생일\t배우성별");
			for (int i = 0; i < actorByMovie.size(); i++) {
				System.out.println(actorByMovie.get(i).actorName + "\t" + actorByMovie.get(i).actorBirth + "\t" + actorByMovie.get(i).actorGender);

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
