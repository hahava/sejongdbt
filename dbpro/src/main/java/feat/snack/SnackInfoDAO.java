package feat.snack;

import oracle.connect.JDBCManager;

import java.util.List;

public class SnackInfoDAO {

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
	public List<SnackInfoDTO> selectSnacks() {
		final String query = "SELECT " +
			"	SNACK_CODE, " +
			"	SNACK_NAME, " +
			"	SNACK_CONTENT, " +
			"	SNACK_CAL, " +
			"	SNACK_PRICE " +
			"FROM " +
			"	SNACK_INFO";

		return JDBCManager
			.getInstance()
			.queryForList(query, SnackInfoDTO.class);
	}
}
