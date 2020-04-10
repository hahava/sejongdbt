package feat.snack;

import oracle.connect.JDBCManager;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;

public class MyuserSnackOrderDAO {

	private MyuserSnackOrderDAO() {
	}

	private static MyuserSnackOrderDAO instance;

	public static MyuserSnackOrderDAO getInstance() {
		if (instance == null) {
			instance = new MyuserSnackOrderDAO();
		}
		return instance;
	}

	// 회원이 주문한 스낵 정보를 출력한다.
	public List<MyuserSnackOrderDTO> selectOrderedSnacks() {
		final String query = "SELECT " +
			"	ORDER_NUM, " +
			"	MYUSER_ID, " +
			"	SNACK_CODE, " +
			"	ORDER_DATE " +
			"FROM " +
			"	MYUSER_SNACK_ORDER";

		return JDBCManager
			.getInstance()
			.queryForList(query, MyuserSnackOrderDTO.class);
	}

	// 로그인한 유저의 구매내역을 보여준다.
	public List<Map<String, Object>> selectMySnackOrders(String userId) {
		final String query = "SELECT " +
			"MSO.ORDER_NUM, " +
			"MSO.MYUSER_ID, " +
			"S.SNACK_NAME, " +
			"S.SNACK_PRICE, " +
			"MSO.ORDER_DATE " +
			"FROM " +
			"MYUSER_SNACK_ORDER MSO, " +
			"SNACK_INFO S " +
			"WHERE " +
			"MSO.SNACK_CODE=S.SNACK_CODE AND MSO.MYUSER_ID=" + StringUtils.wrap(userId, "'");

		return JDBCManager
			.getInstance()
			.queryForMaps(query, new String[] {"MSO.ORDER_NUM", "MSO.MYUSER_ID", "S.SNACK_NAME", "S.SNACK_PRICE"});
	}

	// 가장 간식을 많이 구매한 회원을 출력하는 메서드이다.
	public Map<String, Object> selectMostOrderedMember() {
		/*
		 * myuser_snack_order 테이블과 snack_info 테이블을 조인하고 myuser_id로 그룹핑 한 뒤 회원별
		 * 스낵 총 구매액과 횟수를 보여준다. 이 때 snack_price의 합계가 가장 많은 순서로 정렬한다.
		 */
		final String query = "SELECT " +
			"	MSO.MYUSER_ID AS userId, " +
			"	SUM(SI.SNACK_PRICE) AS sum, " +
			"	COUNT(*) as count " +
			"FROM " +
			"	MYUSER_SNACK_ORDER MSO, " +
			"	SNACK_INFO SI " +
			"WHERE " +
			"	MSO.SNACK_CODE = SI.SNACK_CODE " +
			"GROUP BY MSO.MYUSER_ID " +
			"ORDER BY SUM(SI.SNACK_PRICE) DESC";


		return JDBCManager
			.getInstance()
			.queryForMap(query, new String[] {"userId", "sum", "count"});
	}

	// 최소 스낵 구매액을 지정해주면 그 이상 구매한 사람의 명단을 출력해주는 메서드이다.
	public List<Map<String, Object>>  selectMinPriceOrders(int minPrice) {
		/*
		 * myuser_snack_order 테이블과 snack_info 테이블을 조인하고 myuser_id로 그룹핑 한 뒤 회원별
		 * 스낵 총 구매액과 횟수를 보여준다. 이 때 지정한 스낵 구매액보다 큰 회원을 출력한다.
		 */
		final String query = "SELECT " +
			"	MSO.MYUSER_ID AS userId, " +
			"	SUM(SI.SNACK_PRICE) AS sum , " +
			"	COUNT(*) AS count " +
			"FROM " +
			"	MYUSER_SNACK_ORDER MSO, " +
			"	SNACK_INFO SI " +
			"WHERE " +
			"	MSO.SNACK_CODE=SI.SNACK_CODE " +
			"GROUP BY MSO.MYUSER_ID " +
			"HAVING SUM(SI.SNACK_PRICE)> " + StringUtils.wrap(String.valueOf(minPrice), "'") +
			"ORDER BY SUM(SI.SNACK_PRICE) DESC";

		return JDBCManager
			.getInstance()
			.queryForMaps(query, new String[] {"userId", "sum", "count"});
	}

}
