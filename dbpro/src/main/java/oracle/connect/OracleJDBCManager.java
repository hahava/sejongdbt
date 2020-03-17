package oracle.connect;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class OracleJDBCManager {
	public void registerOracleJDBCDriver() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			System.out.println("Where is your Oracle JDBC Driver?");
			e.printStackTrace();
			return;
		}

	}

	public Connection connect(String id, String passwd, int port) {
		String jdbcUrl = "jdbc:oracle:thin:@sce.sejong.ac.kr:" + port + "/orcl";
		Connection connection = null;

		try {
			connection = DriverManager.getConnection(jdbcUrl, id, passwd);
		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return null;
		}

		if (connection != null) {
			return connection;
		} else {
			System.out.println("Failed to make connection!");
			return null;
		}
	}

	public ResultSet executeSelectQuery(Connection conn, String query) {
		try {
			Statement stmt = conn.createStatement();
			ResultSet rset = stmt.executeQuery("select * from tab");
			return rset;
		} catch (SQLException e) {
			System.out.println(e);
			return null;
		}
	}

	public void printResult(ResultSet rset) {
		/*
		 * In this situation, I print the only first column in result.
		 */
		try {
			while (rset.next()) {
				System.out.println(rset.getString(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}