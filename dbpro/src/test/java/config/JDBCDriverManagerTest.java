package config;

import dto.admin.MovieActorDTO;
import oracle.connect.JDBCManager;
import org.junit.Test;

import java.util.List;

public class JDBCDriverManagerTest {
	@Test
	public void filedTest() {
		List<MovieActorDTO> list = JDBCManager.getInstance()
			.queryForList("select * from MOVIE_ACTOR", MovieActorDTO.class);
		list.forEach(movieActorDTO -> {
			System.out.println(movieActorDTO.toString());
		});
	}

}
