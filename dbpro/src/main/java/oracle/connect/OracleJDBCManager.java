package oracle.connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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
}