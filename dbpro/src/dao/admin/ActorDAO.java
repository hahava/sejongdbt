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

	private Connection getConnection() {
		OracleJDBCManager manager = new OracleJDBCManager();
		String oracleId = "s15010924";
		String passwd = "s15010924";
		int port = 1521;
		manager.registerOracleJDBCDriver();
		Connection conn = manager.connect(oracleId, passwd, port);
		return conn;
	}

	private ActorDAO() {

	}

	private static ActorDAO instance = new ActorDAO();

	public static ActorDAO getInstance() {
		return instance;
	}

	public void list() {

		ArrayList<ActorDTO> actor = new ArrayList<>();

		Connection conn = getConnection();
		PreparedStatement pstm = null;
		ResultSet result = null;
		String query = "select * from ACTOR";

		try {
			pstm = conn.prepareStatement(query);
			result = pstm.executeQuery();
			while (result.next()) {
				actor.add(new ActorDTO(result.getString("actor_code"), result.getString("actor_name"),
						result.getString("actor_gender"), result.getString("actor_birth")));
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
	
	//특정 영화에 출연한 배우 검색
	public void actorSearchByMovie() {
		Scanner sc=new Scanner(System.in);
		Connection conn=getConnection();
		PreparedStatement pstm=null;
		ResultSet result=null;
		String movieName;
		ArrayList<ActorDTO> actorByMovie=new ArrayList<ActorDTO>();
		System.out.println("특정 영화에 출연한 배우를 검색합니다. 영화 명을 입력하세요.");
		movieName=sc.nextLine();
		String query="select * from actor where actor_code in "
				+ "(select actor_code from movie_actor where movie_code in "
				+ "(select movie_code from movie where movie_title='"+movieName+"'))";
		
		try {
			pstm=conn.prepareStatement(query);
			result=pstm.executeQuery();
			while(result.next()) {
				actorByMovie.add(new ActorDTO(result.getString("actor_code"), result.getString("actor_name"),
						result.getString("actor_gender"), result.getString("actor_birth")));
			}
			if(actorByMovie.size()==0) {
				System.out.println("그런 영화가 없거나 관리자가 배우정보를 등록하지 않았습니다.");
				return;
			}
			System.out.println("배우이름\t배우생일\t배우성별");
			for (int i = 0; i < actorByMovie.size(); i++) {
				System.out.println(actorByMovie.get(i).actorName+"\t"+actorByMovie.get(i).actorBirth+"\t"+actorByMovie.get(i).actorGender);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			result.close();
			pstm.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
