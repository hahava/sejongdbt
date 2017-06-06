package dao.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

import dao.admin.ActorDAO;

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
	   
	   public void movieMenu() {
		   ActorDAO actordao=ActorDAO.getInstance();
		   int menu;
		   Scanner sc=new Scanner(System.in);
		   System.out.println("영화 관련 정보 조회 메뉴입니다.");
		   System.out.println("1. 영화 개요 검색");
		   System.out.println("2. 배우기반 영화 검색");
		   System.out.println("3. 특정 영화에 출연한 배우 검색");
		   System.out.println("4. 뒤로");
		   menu=sc.nextInt();
		   
		   switch(menu) {
		   		case 1:
		   			movieInfo();
		   			break;
		   		case 2:
		   			movieSearchByActor();
		   			break;
		   		case 3:
		   			actordao.actorSearchByMovie();
		   			break;
		   		case 4:
		   			return;
		   		default:
		   			System.out.println("잘못 입력하셨습니다.");
		   			break;
		   }
		   
	   }
	   
	   // 영화 정보 보여주기 - 영화 개요 검색
	   public void movieInfo() {
		   Connection conn=getConnection();
		   PreparedStatement pstm=null;
		   ResultSet result=null;
		   Scanner sc=new Scanner(System.in);
		   System.out.println("영화 정보를 출력합니다. 조회를 원하는 영화이름을 입력하세요.(* : 배우와 모두 출력)");
		   String movieName=sc.nextLine();
		 
		   
		   if(movieName.compareTo("*")==0) {
			  String query="select m.movie_code,movie_title, movie_director,movie_age, movie_genre, movie_start, movie_end, a.actor_name, ma.actor_role, a.actor_birth, a.actor_gender " + 
			  		"from movie m, movie_actor ma, actor a " + 
			  		"where ma.MOVIE_CODE=m.MOVIE_CODE and ma.ACTOR_CODE=a.ACTOR_CODE " + 
			  		"order by m.movie_code";
			  try {
				pstm=conn.prepareStatement(query);
				result=pstm.executeQuery();
				System.out.println("영화코드\t영화제목\t\t영화감독\t연령\t장르\t상영일\t종영일\t배우이름\t역할\t생일\t성별");
				
				while(result.next()) {
					System.out.println(result.getString(1)+"\t"+result.getString(2)+"\t\t"+result.getString(3)+"\t"+
									   result.getInt(4)+"\t"+result.getString(5)+"\t"+result.getDate(6)+"\t"+result.getDate(7)+"\t"+
									   result.getString(8)+"\t"+result.getString(9)+"\t"+result.getDate(10)+"\t"+result.getString(11));
					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		   }
		   else {
			   ArrayList<MovieDTO> movie = new ArrayList<>();

				String query = "select * from movie where movie_title='"+movieName+"'";

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
		   
		   
		   
	   }
	   
	   // 특정 배우가 출연한 영화 검색(subquery)
	   public void movieSearchByActor() {
		   Connection conn=getConnection();
		   PreparedStatement pstm=null;
		   ResultSet result=null;
		   Scanner sc=new Scanner(System.in);
		   System.out.println("특정 배우가 출연한 영화를 검색합니다. 배우 이름을 입력하세요.");
		   String actorName=sc.nextLine();
		   ArrayList<MovieDTO> movie=new ArrayList<MovieDTO>();
		   String query="select  distinct * from movie where movie_code in "
		   		+ "(select movie_code from movie_actor where actor_code in "
		   		+ "(select actor_code from actor where actor_name='"+actorName+"'))";
		  
		   try {
			pstm=conn.prepareStatement(query);
			result=pstm.executeQuery();
			while(result.next()) {
				movie.add(new MovieDTO(result.getString("MOVIE_CODE"), result.getString("MOVIE_TITLE"), result.getString("MOVIE_DIRECTOR"),
						result.getInt("MOVIE_AGE"), result.getString("MOVIE_GENRE"), result.getDate("MOVIE_START"), result.getDate("MOVIE_END")));
			}
			if(movie.size()==0) {
				System.out.println("그런 배우가 없거나, 아직 출연한 작품이 없습니다.");
				return;
			}
			
			System.out.println("영화코드\t영화제목\t\t영화감독\t연령\t장르\t개봉일\t종영일");
			for(int i=0;i<movie.size();i++) {
				System.out.println(movie.get(i).MOVIE_CODE+"\t"+movie.get(i).MOVIE_TITLE+"\t\t"+movie.get(i).MOVIE_DIRECTOR+"\t"+
						movie.get(i).MOVIE_AGE+"\t"+movie.get(i).MOVIE_GENRE+"\t"+movie.get(i).MOVIE_START+"\t\t"+movie.get(i).MOVIE_END+"\t");		
			
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
	   
	   public void pickBestMovieOne() {
			String query;
			Connection conn=getConnection();
			PreparedStatement pstm=null;
			ResultSet result=null;
			
			query="select m.myuser_id,count(*) from myuser m, movie_payment mp "
					+ "where m.myuser_id=mp.myuser_id group by m.myuser_id "
					+ "having count(*)=(select max(count(*))"
					+ " from movie_payment group by myuser_id)";
			
		
			try {
				pstm=conn.prepareStatement(query);
				result=pstm.executeQuery();
				System.out.println("아이디\t횟수");
				while(result.next()) {
					System.out.println(result.getString(1)+"\t"+result.getInt(2));
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
		
		public void pickBestMoviePeople() {
			String query;
			Connection conn=getConnection();
			PreparedStatement pstm=null;
			ResultSet result=null;
			Scanner sc=new Scanner(System.in);
			query="select m.myuser_id,count(*) from myuser m, movie_payment mp "
					+ "where m.myuser_id=mp.myuser_id group by m.myuser_id "
					+ "having count(*)>=?";
			System.out.println("범위를 지정하세요.입력한 숫자 이상 영화를 본 회원들을 출력합니다.");
			int num=sc.nextInt();
			
			try {
				pstm=conn.prepareStatement(query);
				pstm.setInt(1, num);
				result=pstm.executeQuery();
				
				System.out.println("아이디\t횟수");
				while(result.next()) {
					System.out.println(result.getString(1)+"\t"+result.getInt(2));
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
		
		
		public void movieStaticsMenu() {
			int menu;
			Scanner sc=new Scanner(System.in);
			System.out.println("관리자용 영화 상세 통계 메뉴입니다.");
			System.out.println("1. 영화 별 평점 통계");
			System.out.println("2. 영화별 광고비 합계");
			System.out.println("3. 뒤로");
			menu=sc.nextInt();
			
			switch(menu) {
				case 1:
					movieRatStatic();
					break;
				case 2:
					movieADStatic();
					break;
				case 3:
					return;
				default:
					System.out.println("잘못 입력하셨습니다.");
					break;
			
			}
		}
		

		public void movieRatStatic() {
			double minVal;
			Connection conn=getConnection();
			PreparedStatement pstm=null;
			ResultSet result=null;
			Scanner sc=new Scanner(System.in);
			System.out.println("영화별 평점 통계입니다.");
			System.out.println("최소 범위를 입력해주세요.(0~5)(0 입력시, 전체 출력)");
			minVal=sc.nextDouble();
			
			String query="select m.movie_code,m.movie_title, avg(r.rat_point), count(m.movie_code) from movie m, rat r " + 
					"where m.movie_code=r.movie_code " + 
					"group by m.movie_code, m.movie_title " + 
					"having avg(r.rat_point)>=?";
			try {
				pstm=conn.prepareStatement(query);
				pstm.setDouble(1, minVal);
				result=pstm.executeQuery();
				if(result.next()!=true) {
					System.out.println("범위를 잘못 설정하셨습니다. 다시 시도해주세요.");
					return;
				}
				System.out.println("영화코드\t영화제목\t\t\t평점평균\t참여회원수");
				do{
					System.out.println(result.getString(1)+"\t"+result.getString(2)+"\t\t\t"+result.getDouble(3)+"\t"+result.getInt(4));
				}while(result.next());
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
		
		public void movieADStatic() {
			System.out.println("영화별 광고 통계입니다. 영화에 할당된 광고료가 높은 순서로 출력됩니다.");
			Connection conn=getConnection();
			PreparedStatement pstm=null;
			ResultSet result=null;
			String query="select m.movie_code, m.movie_title, sum(a.ad_price), count(*) " + 
					"from movie m, movie_ad ma, ad a " + 
					"where m.movie_code=ma.movie_code and ma.AD_TITLE=a.AD_TITLE " + 
					"group by m.movie_code, m.movie_title " + 
					"order by sum(a.ad_price) desc";
			
			try {
				pstm=conn.prepareStatement(query);
				result=pstm.executeQuery();
				System.out.println("영화코드\t영화제목\t\t\t총 광고료\t\t할당광고수");
				while(result.next()) {
					System.out.println(result.getString(1)+"\t"+result.getString(2)+"\t\t\t"+result.getInt(3)+"\t\t"+result.getInt(4));
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
