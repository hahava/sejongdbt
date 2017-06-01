package dao.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;
import java.sql.Date;
import dto.user.*;
import oracle.connect.OracleJDBCManager;

public class MovieDAO implements DAO {

	// 인스턴스를 리턴한다.
	private static MovieDAO instance = new MovieDAO();

	// 프라이빗 생성자를 만들어서 캡슐화
	private MovieDAO() {
	}

	public static MovieDAO getInstance() {
		return instance;
	}

	private Connection getConnection() {
		OracleJDBCManager manager = new OracleJDBCManager();
		String oracleId = "s15010924";
		String passwd = "s15010924";
		int port = 1521;
		manager.registerOracleJDBCDriver();
		Connection conn = manager.connect(oracleId, passwd, port);
		return conn;
	}

	// private static

	public void list() {

		ArrayList<MovieDTO> movie = new ArrayList<>();

		Connection conn = getConnection();
		PreparedStatement pstm = null;
		ResultSet result = null;
		String query = "select * from movie";

		try {
			pstm = conn.prepareStatement(query);
			result = pstm.executeQuery();
			while (result.next()) {
				movie.add(new MovieDTO(result.getString("MOVIE_CODE"), result.getString("MOVIE_TITLE"), result.getString("MOVIE_DIRECTOR"),
						result.getInt("MOVIE_AGE"), result.getString("MOVIE_GENRE"), result.getDate("MOVIE_START"), result.getDate("MOVIE_END")));
			}

		} catch (SQLException e1) {
			System.out.println(e1);
			System.out.println(e1.getMessage());
		}

		try {
			result.close();
			pstm.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 해다열 전부 출력
		for (int i = 0; i < movie.size(); i++) {
			System.out.println(movie.get(i).toString());
		}
	}
	   @SuppressWarnings("resource")
	   public void insertMovie() {
	      int insertMenu;
	      Scanner scanner=new Scanner(System.in);
	      System.out.println("영화 정보를 삽입합니다. (0번을 누를 시 돌아갑니다)");
	      
	      insertMenu=scanner.nextInt();
	      if(insertMenu == 0) {
	         return;
	      }
	      MovieDTO mvdto=new MovieDTO();
	      Connection conn=getConnection();
	      PreparedStatement pstm=null;
	      System.out.print("영화 코드 : ");
	      mvdto.MOVIE_CODE=scanner.nextLine();
	      System.out.print("영화 제목 (25자 내): ");
	      mvdto.MOVIE_TITLE=scanner.nextLine();
	      System.out.print("영화 감독 : ");
	      mvdto.MOVIE_AGE=scanner.nextInt();
	      System.out.print("영화 장르 : ");
	      mvdto.MOVIE_GENRE=scanner.nextLine();
	      System.out.print("상영시작 : ");
	      SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
	      String temp = scanner.nextLine();
	      try {
	         mvdto.MOVIE_START = (Date) dateFormat.parse(temp);
	      } catch (ParseException e) {
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	      }
	      
	      System.out.print("상영 끝 : ");
	      temp=scanner.nextLine();
	      try {
	         mvdto.MOVIE_END=(Date) dateFormat.parse(temp);
	      } catch (ParseException e) {
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	      }
	      try {
	         pstm=conn.prepareStatement("insert into movie values(?,?,?,?,?,?)");
	         pstm.setString(1, mvdto.MOVIE_CODE);
	         pstm.setString(2, mvdto.MOVIE_TITLE);
	         pstm.setString(3, mvdto.MOVIE_DIRECTOR);
	         pstm.setString(4, mvdto.MOVIE_GENRE);
	         pstm.setDate(5, mvdto.MOVIE_START);
	         pstm.setDate(6, mvdto.MOVIE_END);
	         pstm.executeUpdate();
	         
	         
	         pstm = conn.prepareStatement("COMMIT");
	         pstm.executeUpdate();

	      }catch(SQLException sqlException) {
	         System.out.println("문제가 발생했습니다. 관리자에게 문의하세요!");
	         
	      }finally {
	         try {
	            pstm.close();
	            conn.close();
	         } catch (SQLException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	         }
	         
	      }
	   }
	   
	   
	   // 만약 이게 안된다면 Date부분에 
	   /*
	    * INSERT INTO emp (eno, ename, hdate) VALUES ('2000','안민정', TO_DATE('1991-05-17:12:00:13','YYYY-MM-DD:HH24:MI:SS'));
	    * TO_DATE('날짜~','날짜 형식') << 함수를 써보세용
	    * 
	   */
}
