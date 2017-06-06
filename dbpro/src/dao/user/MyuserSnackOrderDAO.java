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
		manager.registerOracleJDBCDriver();
		Connection conn = manager.connect(oracleId, passwd, port);
		return conn;
	}

	private MyuserSnackOrderDAO() {
	}

	private static MyuserSnackOrderDAO instance = new MyuserSnackOrderDAO();

	public static MyuserSnackOrderDAO getInstance() {
		return instance;
	}

	@Override
	public void list() {

		ArrayList<MyuserSnackOrderDTO> arrayList = new ArrayList<>();

		Connection conn = getConnection();
		PreparedStatement pstm = null;
		ResultSet result = null;
		String query = "select * from myuser_snack_order";

		try {
			pstm = conn.prepareStatement(query);
			result = pstm.executeQuery();
			while (result.next()) {
				arrayList.add(new MyuserSnackOrderDTO(result.getInt("ORDER_NUM"), result.getString("MYUSER_ID"),
						result.getString("SNACK_CODE"), result.getDate("ORDER_DATE")));
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void listMe(String id) {

		OracleJDBCManager manager = new OracleJDBCManager();
		String oracleId = "s15010924";
		String passwd = "s15010924";
		int port = 1521;
		manager.registerOracleJDBCDriver();

		ArrayList<MyuserSnackOrderDTO> arrayList = new ArrayList<>();

		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet result = null;
		String query = "select * from myuser_snack_order where MYUSER_ID = ?";

		conn = manager.connect(oracleId, passwd, port);
		try {
			pstm = conn.prepareStatement(query);
			pstm.setString(1, ExecuteProject.id);
			result = pstm.executeQuery();
			while (result.next()) {
				arrayList.add(new MyuserSnackOrderDTO(result.getInt("ORDER_NUM"), result.getString("MYUSER_ID"),
						result.getString("SNACK_CODE"), result.getDate("ORDER_DATE")));
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
		for (int i = 0; i < arrayList.size(); i++) {
			System.out.println(arrayList.get(i).toString());
		}
	}
	
	public void pickBestSnackOne() {
		
		String query="select mso.myuser_id, sum(si.snack_price), count(*) from myuser_snack_order mso, snack_info si " + 
				"where mso.snack_code=si.snack_code " + 
				"group by mso.myuser_id " + 
				"order by sum(si.snack_price) desc";
		Connection conn=getConnection();
		PreparedStatement pstm=null;
		ResultSet result=null;
		System.out.println("가장 스낵에 돈을 많이 투자한 회원을 출력합니다.");
		
		try {
			pstm=conn.prepareStatement(query);
			result=pstm.executeQuery();
			
			if(result.next()) {
				System.out.println("아이디\t\t사용금액\t구입횟수");
				System.out.println(result.getString(1)+"\t\t"+result.getInt(2)+"\t"+result.getInt(3));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void pickBestSnackPeople() {
		String query="select mso.myuser_id, sum(si.snack_price), count(*) from myuser_snack_order mso, snack_info si " + 
				"where mso.snack_code=si.snack_code " + 
				"group by mso.myuser_id " + 
				"having sum(si.snack_price)>? "
				+ "order by sum(si.snack_price) desc";
		Connection conn=getConnection();
		PreparedStatement pstm=null;
		ResultSet result=null;
		Scanner sc=new Scanner(System.in);
		System.out.println("스낵에 돈을 많이 투자한 회원을 출력합니다. 범위를 지정하세요.");
		int minMoney=sc.nextInt();
		try {
			pstm=conn.prepareStatement(query);
			pstm.setInt(1, minMoney);
			result=pstm.executeQuery();
			System.out.println("아이디\t\t사용금액\t구입횟수");
			if(result.next()!=true) {
				System.out.println("범위를 다시 설정하세요.");
				return;
			}
			do {
				System.out.println(result.getString(1)+"\t"+result.getInt(2)+"\t"+result.getInt(3));
			}while(result.next());
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
