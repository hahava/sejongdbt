package kr.ac.sejong.config;

import kr.ac.sejong.feat.actor.MovieActorDTO;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;
import java.util.Map;

public class JDBCManagerTest {
	@Test
	public void filedTest() {
		List<MovieActorDTO> list = JDBCManager.getInstance()
			.queryForList("select * from MOVIE_ACTOR", MovieActorDTO.class);
		list.forEach(movieActorDTO -> {
			System.out.println(movieActorDTO.toString());
		});
	}

	@Test
	public void queryMapTest() {
		final String query = "SELECT \n" +
			"COUNT(*) as count, AVG(employee_task_sal) as average\n" +
			"FROM\n" +
			"    employee em\n" +
			"        JOIN\n" +
			"    employee_task emt ON em.employee_role = emt.employee_role\n" +
			"WHERE\n" +
			"    employee_age BETWEEN 20 AND 30";
		Map<String, Object> result = JDBCManager.getInstance().queryForMap(query, new String[] {"count", "average"});
		result.forEach((key, value) -> System.out.println(key + "\t" + value));
	}

	@Test
	@Ignore
	public void delete() {
		final String query = "DELETE FROM MOVIE WHERE MOVIE_CODE = ?";
		JDBCManager
			.getInstance()
			.delete(query, new String[] {"M1"});

	}

	@Test
	public void update() {
		final String query = "UPDATE MOVIE" +
			" SET MOVIE_TITLE = ?, " +
			"	MOVIE_DIRECTOR = ?, " +
			"	MOVIE_AGE= ?, " +
			"	MOVIE_GENRE= ?, " +
			"	MOVIE_START=?, " +
			"	MOVIE_END=? " +
			"WHERE " +
			"	MOVIE_CODE =?";

		JDBCManager.getInstance().update(query, new Object[]{"hello world","hello world",10,"M","2019-10-01","2019-10-01","M2"});
	}

}
