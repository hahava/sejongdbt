package config;

import dto.admin.MovieActorDTO;
import oracle.connect.JDBCManager;
import org.junit.Test;

import java.util.List;

public class JDBCDriverManagerTest {
	@Test
	public void filedTest() {
		JDBCManager jdbcManager = new JDBCManager();
		List<MovieActorDTO> list = jdbcManager
			.queryForList("select * from MOVIE_ACTOR", MovieActorDTO.class);
		list.forEach(movieActorDTO -> {
			System.out.println(movieActorDTO.toString());
		});
	}

}
