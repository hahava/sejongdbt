package kr.ac.sejong.feat.advertisement;

import kr.ac.sejong.config.JDBCManager;

import java.util.List;

public class AdDAO {

	private AdDAO() {
	}

	private static AdDAO instance;

	public static AdDAO getInstance() {
		if (instance == null) {
			instance = new AdDAO();
		}
		return instance;
	}

	public List<AdDTO> selectAdvertisements() {
		final String query = "select AD_TITLE, " +
			"	AD_COMPANY, " +
			"	AD_DATE, " +
			"	AD_PRICE " +
			"from " +
			"	AD";

		return JDBCManager
			.getInstance()
			.queryForList(query, AdDTO.class);
	}

}
