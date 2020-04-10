package feat.user;

import config.JDBCManager;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;

public class MyuserDAO {

	private static MyuserDAO instance;

	// 캡슐화
	private MyuserDAO() {
	}

	// 만들어진 인스턴스 리턴
	public static MyuserDAO getInstance() {
		if (instance == null) {
			instance = new MyuserDAO();
		}
		return instance;
	}

	// myuser테이블 전부 출력
	public List<MyuserDTO> selectUsers() {
		final String query = "SELECT " +
			"	MYUSER_ID, " +
			"	MYUSER_NAME, " +
			"	MYUSER_PW, " +
			"	MYUSER_BIRTH, " +
			"	MYUSER_PHONE, " +
			"	MYUSER_EMAIL " +
			"FROM " +
			"	MYUSER";

		return JDBCManager
			.getInstance()
			.queryForList(query, MyuserDTO.class);
	}

	// 로그인 한 유저의 정보만을 출력한다.
	public void selectUserInfo(String id) {

		final String query = "SELECT " +
			"	MYUSER_ID, " +
			"	MYUSER_NAME, " +
			"	MYUSER_PW, " +
			"	MYUSER_BIRTH, " +
			"	MYUSER_PHONE, " +
			"	MYUSER_EMAIL " +
			"FROM " +
			"	MYUSER " +
			"WHERE " +
			"	MYUSER_ID = " + StringUtils.wrap(id, "'");

		JDBCManager
			.getInstance()
			.queryForList(query, MyuserDTO.class)
			.forEach(myuserDTO -> System.out.println(myuserDTO.toString()));
	}

	// 로그인 메서드 id와 password를 입력받아 myuser테이블안에 저장되어 있는지 확인한다. 로그인 가능 여부를 확인 하는 메서드
	public Map<String, Object> login(String id, String pw) {
		final String query = "SELECT " +
			"	MYUSER_ID " +
			"FROM " +
			"	MYUSER " +
			"WHERE " +
			"	MYUSER_ID = " + StringUtils.wrap(id, "'") + " " +
			"AND " +
			"	MYUSER_PW = " + StringUtils.wrap(pw, "'");

		return JDBCManager
			.getInstance()
			.queryForMap(query, new String[] {"MYUSER_ID"});

	}

}
