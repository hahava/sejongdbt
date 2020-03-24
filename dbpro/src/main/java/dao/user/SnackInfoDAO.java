package dao.user;

import dto.user.SnackInfoDTO;
import oracle.connect.JDBCManager;

public class SnackInfoDAO implements DAO {

	private SnackInfoDAO() {
	}

	private static SnackInfoDAO instance;

	public static SnackInfoDAO getInstance() {
		if (instance == null) {
			instance = new SnackInfoDAO();
		}
		return instance;
	}

	// 스낵관련 정보를 전부 출력한다.
	@Override
	public void list() {
		final String query = "SELECT " +
			"	SNACK_CODE, " +
			"	SNACK_NAME, " +
			"	SNACK_CONTENT, " +
			"	SNACK_CAL, " +
			"	SNACK_PRICE " +
			"FROM " +
			"	SNACK_INFO";

		JDBCManager
			.getInstance()
			.queryForList(query, SnackInfoDTO.class)
			.forEach(snackInfoDTO -> System.out.println(snackInfoDTO.toString()));
	}
}
