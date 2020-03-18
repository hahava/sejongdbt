package dao;

import dto.admin.AdDTO;
import oracle.connect.JDBCManager;
import org.junit.Test;

public class AdDAOTest {

	@Test
	public void getAdListTest() {
		final String query = "SELECT AD_TITLE, AD_COMPANY,AD_DATE, AD_PRICE FROM AD";
		JDBCManager
			.getInstance()
			.queryForList(query, AdDTO.class)
			.forEach(adDTO -> System.out.println(adDTO.toString()));
	}

}
