package oracle.connect;

import org.apache.commons.lang3.StringUtils;
import util.PropertiesWrapper;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

public class JDBCManager {

	static {
		try {
			Class.forName(PropertiesWrapper.getInstance().getProperty("jdbc.driver"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public <T> List<T> queryForList(String query, Class<T> elementType) {
		Properties properties = PropertiesWrapper.getInstance();
		List<T> queryResults = new LinkedList<>();
		try (Connection connection = DriverManager.getConnection(properties.getProperty("jdbc.host"), "root", "");
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(query)) {
			while (resultSet.next()) {
				T queryRow = elementType.newInstance();
				for (Field field : elementType.getDeclaredFields()) {
					String columnName = field.getAnnotation(ColumnName.class).value();
					field.setAccessible(true);
					field.set(queryRow, resultSet.getObject(columnName));
				}
				queryResults.add(queryRow);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IllegalAccessException | InstantiationException ie) {
			ie.printStackTrace();
		}
		return queryResults;
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