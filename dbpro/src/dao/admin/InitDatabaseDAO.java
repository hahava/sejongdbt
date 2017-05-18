package dao.admin;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import dao.user.DAO;
import dto.user.*;
import oracle.connect.OracleJDBCManager;
import SqlRunner.SqlRunner;

public class InitDatabaseDAO implements DAO {

	
	
	@Override
	public void list() {
		// TODO Auto-generated method stub
		OracleJDBCManager manager = new OracleJDBCManager();
		String oracleId = "s15010924";
		String passwd = "s15010924";
		String[] tableName= {"MOVIE","MYUSER","RAT","PAYMENT","MOVIE_PAYMENT","SNACK_INFO","MYUSER_SNACK_ORDER",
							"EMPLOYEE_TASK","EMPLOYEE","AD","MOVIE_AD","ACTOR","MOVIE_ACTOR","PAYMENT_STATICS"};
		String[] query=new String[14];
		String[] sequence= {"MOVIE_PAYMENT_INC","EMPLOYEE_INC","MOVIE_AD_INC","SNACK_ORDER_INC"};
		int port = 1521;
		int resultnum;
		manager.registerOracleJDBCDriver();
		
		Connection conn = null;
		PreparedStatement pstm = null;
		CallableStatement cstm=null;
		conn = manager.connect(oracleId, passwd, port);
		
		ArrayList<String> temp;
		SqlRunner sr=new SqlRunner();
		
		try {
			
			for(int i=0;i<tableName.length;i++) {
			query[i]="DELETE FROM "+tableName[i];
			pstm=conn.prepareStatement(query[i]);
			resultnum=pstm.executeUpdate();
			System.out.println("데이터를 모두 삭제했습니다..");

			}

			pstm=conn.prepareStatement("COMMIT");
			pstm.executeUpdate();
			
			for(int i=0;i<sequence.length;i++) {
			cstm=conn.prepareCall("{call reset_sequence(?)}");
			cstm.setString(1, sequence[i]);
			cstm.executeUpdate();
			}
			

			temp=sr.createQueries("./sql/INSERT_DATA_ver2.sql");
//			for(String s : temp) {
//				System.out.println(s);
//		}		
		for(String insertQuery : temp) {
		
			pstm=conn.prepareStatement(insertQuery);
			pstm.executeUpdate();
		}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("실행도중 오류가 발생했습니다. 관리자에게 문의하세요.");
			System.out.println(e.getSQLState());
			e.printStackTrace();
		}
		try {
			cstm.close();
			pstm.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
