package kr.ac.sejong.config;

import kr.ac.sejong.util.PropertiesWrapper;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.*;

public class JDBCManager {

	static {
		try {
			Class.forName(PropertiesWrapper.getInstance().getProperty("jdbc.driver"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private static JDBCManager jdbcManager;

	private JDBCManager() {
	}

	public static JDBCManager getInstance() {
		if (jdbcManager == null) {
			jdbcManager = new JDBCManager();
		}
		return jdbcManager;
	}

	private Connection getConnection() throws SQLException {
		Properties properties = PropertiesWrapper.getInstance();
		return DriverManager.getConnection(properties.getProperty("jdbc.host"), "root", "");
	}

	public <T> List<T> queryForList(String query, Class<T> elementType) {
		List<T> queryResults = new LinkedList<>();
		try (Connection connection = getConnection();
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

	public Map<String, Object> queryForMap(String query, String[] columns) {
		Map<String, Object> result = new HashMap<>();
		try (Connection connection = getConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(query)) {
			while (resultSet.next()) {
				for (String column : columns) {
					result.put(column, resultSet.getObject(column));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public List<Map<String, Object>> queryForMaps(String query, String[] columns) {
		List<Map<String, Object>> results = new LinkedList<>();
		try (Connection connection = getConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(query)) {
			while (resultSet.next()) {
				Map<String, Object> row = new HashMap<>();
				for (String column : columns) {
					row.put(column, resultSet.getObject(column));
				}
				results.add(row);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return results;
	}

	public int insert(String query, Object[] params) {
		return update(query, params);
	}

	public int delete(String query, Object[] params) {
		return update(query, params);
	}

	public int update(String query, Object[] params) {
		int result = 0;
		try (Connection connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(query)
		) {
			for (int i = 0; i < params.length; i++) {
				statement.setObject(i + 1, params[i]);
			}
			result = statement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
		}
		return result;
	}

}