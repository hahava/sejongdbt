package dao.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import dto.user.*;
import main.ExecuteProject;
import oracle.connect.OracleJDBCManager;

public class MoviePaymentDAO implements DAO {

	private static MoviePaymentDAO instance = new MoviePaymentDAO();

	private MoviePaymentDAO() {
	}

	public static MoviePaymentDAO getInstance() {
		return instance;
	}

	private Connection getConnection() {
		OracleJDBCManager manager = new OracleJDBCManager();
		String oracleId = "s15010924";
		String passwd = "s15010924";
		int port = 1521;

		Connection conn = manager.connect(oracleId, passwd, port);
		return conn;
	}

	public void list() {

		ArrayList<MoviePaymentDTO> arrayList = new ArrayList<>();

		Connection conn = getConnection();
		PreparedStatement pstm = null;
		ResultSet result = null;
		String query = "select * from movie_payment";

		try {
			pstm = conn.prepareStatement(query);
			result = pstm.executeQuery();
			while (result.next()) {
				arrayList.add(new MoviePaymentDTO(result.getInt("MOVIE_PAYMENT_CODE"), result.getString("MYUSER_ID"), result.getString("MOVIE_CODE"),
						result.getString("PAYMENT_CODE"), result.getDate("PAYMENT_DATE")));
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

	public void listMe(String id) {


		Connection conn = getConnection();
		PreparedStatement pstm = null;
		ResultSet result = null;
		String query = "select mp.movie_payment_code, mp.myuser_id, m.movie_title, m.movie_age, p.payment_way, mp.payment_date " + 
				"from movie_payment mp, movie m, payment p " + 
				"where mp.movie_code=m.movie_code and mp.payment_code=p.payment_code and mp.myuser_id=?";

		try {
			pstm = conn.prepareStatement(query);
			pstm.setString(1, ExecuteProject.id);
			result = pstm.executeQuery();
			System.out.println("결제번호\t아이디\t영화이름\t\t연령\t결제수단\t결제일");
			while (result.next()) {
				System.out.println(result.getString(1)+"\t"+result.getString(2)+"\t"+result.getString(3)+"\t\t"+
			result.getInt(4)+"\t"+result.getString(5)+"\t"+result.getDate(6));
				
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

	

}
