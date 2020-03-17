package dao.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import dto.user.MyuserSnackOrderDTO;
import main.ExecuteProject;
import oracle.connect.OracleJDBCManager;

public class MyuserSnackOrderDAO implements DAO {
	private Connection getConnection() {
		OracleJDBCManager manager = new OracleJDBCManager();
		String oracleId = "s15010924";
		String passwd = "s15010924";
		int port = 1521;

		Connection conn = manager.connect(oracleId, passwd, port);
		return conn;
	}

	private MyuserSnackOrderDAO() {
	}

	private static MyuserSnackOrderDAO instance = new MyuserSnackOrderDAO();

	public static MyuserSnackOrderDAO getInstance() {
		return instance;
	}

	// 회원이 주문한 스낵 정보를 출력한다.
	@Override
	public void list() {

		ArrayList<MyuserSnackOrderDTO> arrayList = new ArrayList<>();

		Connection conn = getConnection();
		PreparedStatement pstm = null;
		ResultSet result = null;
		String query = "select ORDER_NUM, MYUSER_ID, SNACK_CODE, ORDER_DATE  from myuser_snack_order";

		try {
			pstm = conn.prepareStatement(query);
			result = pstm.executeQuery();
			while (result.next()) {
				arrayList.add(new MyuserSnackOrderDTO(result.getInt("ORDER_NUM"), result.getString("MYUSER_ID"), result.getString("SNACK_CODE"),
						result.getDate("ORDER_DATE")));
			}
			for (int i = 0; i < arrayList.size(); i++) {
				System.out.println(arrayList.get(i).toString());
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

	// 로그인한 유저의 구매내역을 보여준다.
	public void listMe(String id) {

		Connection conn = getConnection();
		PreparedStatement pstm = null;
		ResultSet result = null;
		String query = "select mso.order_num,mso.myuser_id, s.snack_name, s.snack_price, mso.order_date"
				+ " from myuser_snack_order mso, snack_info s " + "where mso.snack_code=s.snack_code and mso.myuser_id=?";

		try {
			pstm = conn.prepareStatement(query);
			pstm.setString(1, ExecuteProject.id);
			result = pstm.executeQuery();
			System.out.println("주문번호\t아이디\t간식이름\t\t\t가격\t주문일자");
			while (result.next()) {
				System.out.println(result.getInt(1) + "\t" + result.getString(2) + "\t" + result.getString(3) + "\t\t\t" + result.getInt(4) + "\t"
						+ result.getDate(5));
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

	// 가장 간식을 많이 구매한 회원을 출력하는 메서드이다.
	public void pickBestSnackOne() {
		/*
		 * myuser_snack_order 테이블과 snack_info 테이블을 조인하고 myuser_id로 그룹핑 한 뒤 회원별
		 * 스낵 총 구매액과 횟수를 보여준다. 이 때 snack_price의 합계가 가장 많은 순서로 정렬한다.
		 */
		String query = "select mso.myuser_id, sum(si.snack_price), count(*) from myuser_snack_order mso, snack_info si "
				+ "where mso.snack_code=si.snack_code " + "group by mso.myuser_id " + "order by sum(si.snack_price) desc";

		Connection conn = getConnection();
		PreparedStatement pstm = null;
		ResultSet result = null;

		System.out.println("가장 스낵에 돈을 많이 투자한 회원을 출력합니다.");

		try {
			pstm = conn.prepareStatement(query);
			result = pstm.executeQuery();

			if (result.next()) {
				System.out.println("아이디\t\t사용금액\t구입횟수");
				System.out.println(result.getString(1) + "\t\t" + result.getInt(2) + "\t" + result.getInt(3));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 최소 스낵 구매액을 지정해주면 그 이상 구매한 사람의 명단을 출력해주는 메서드이다.
	public void pickBestSnackPeople() {
		/*
		 * myuser_snack_order 테이블과 snack_info 테이블을 조인하고 myuser_id로 그룹핑 한 뒤 회원별
		 * 스낵 총 구매액과 횟수를 보여준다. 이 때 지정한 스낵 구매액보다 큰 회원을 출력한다.
		 */
		String query = "select mso.myuser_id, sum(si.snack_price), count(*) from myuser_snack_order mso, snack_info si "
				+ "where mso.snack_code=si.snack_code " + "group by mso.myuser_id " + "having sum(si.snack_price)>? "
				+ "order by sum(si.snack_price) desc";
		Connection conn = getConnection();
		PreparedStatement pstm = null;
		ResultSet result = null;
		Scanner sc = new Scanner(System.in);
		System.out.println("스낵에 돈을 많이 투자한 회원을 출력합니다. 범위를 지정하세요.");
		int minMoney = sc.nextInt();
		try {
			pstm = conn.prepareStatement(query);
			pstm.setInt(1, minMoney);
			result = pstm.executeQuery();
			System.out.println("아이디\t\t사용금액\t구입횟수");
			if (result.next() != true) {
				System.out.println("범위를 다시 설정하세요.");
				return;
			}
			do {
				System.out.println(result.getString(1) + "\t" + result.getInt(2) + "\t" + result.getInt(3));
			} while (result.next());

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
