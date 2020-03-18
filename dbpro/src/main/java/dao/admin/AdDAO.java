package dao.admin;

import dto.admin.AdDTO;
import oracle.connect.JDBCManager;

public class AdDAO implements DAO {

	private AdDAO() {
	}

	private static AdDAO instance;

	public static AdDAO getInstance() {
		if (instance == null) {
			instance = new AdDAO();
		}
		return instance;
	}

	@Override
	public void list() {
		final String query = "select AD_TITLE, AD_COMPANY,AD_DATE, AD_PRICE from AD";
		JDBCManager
			.getInstance()
			.queryForList(query, AdDTO.class)
			.forEach(adDTO -> System.out.println(adDTO.toString()));
	}

}
