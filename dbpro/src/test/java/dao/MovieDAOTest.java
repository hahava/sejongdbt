package dao;

import dao.admin.MovieAdDAO;
import feat.movie.MovieDAO;
import feat.movie.MovieDTO;
import org.junit.Ignore;
import org.junit.Test;

import java.sql.Date;

public class MovieDAOTest {

	@Test
	public void getMovieAdTest() {
		MovieAdDAO.getInstance().list();
	}

	@Test
	public void getMoviesTest() {
		MovieAdDAO.getInstance().list();
	}

	@Test
	public void getMovieInfoTest(){
		MovieDAO.getInstance().selectMoviesWithActors("어느날 그가 죽었다");
	}

	@Test
	public void getMoviesByActorNameTest(){
		MovieDAO.getInstance().selectMoviesByActor("박지성");
	}

	@Ignore
	@Test
	public void deleteMovieByMovieCode(){
		int result = MovieDAO.getInstance().deleteMovie("M1");
	}

	@Ignore
	@Test
	public void updateTest() {
		MovieDTO movieDTO = new MovieDTO();
		movieDTO.setMovieTitle("movie title");
		movieDTO.setMovieDirector("kalin");
		movieDTO.setMovieAge(10);
		movieDTO.setMovieGenre("action");
		movieDTO.setMovieStart(new Date(2019, 10,1));
		movieDTO.setMovieEnd(new Date(2019, 11, 1));
		movieDTO.setMovieCode("M2");
		MovieDAO.getInstance().updateMovie(movieDTO);
	}

	@Test
	public void getMovieReservationCountTest(){
		MovieDAO.getInstance().getMovieReservationCountOfPerson(1);
	}

	@Test
	public void getPersonWhoBookedTheMostMoviesTest(){
		MovieDAO.getInstance().getPersonWhoBookedTheMostMovies();
	}

	@Test
	public void getMovieRatStaticTest(){
		MovieDAO.getInstance().selectMovieRatStatic(4);
	}

	@Test
	public void getMovieADStaticTest(){
		MovieDAO.getInstance().selectMovieADStatic();
	}

	@Test
	public void insertMovie(){
		MovieDTO movieDTO = new MovieDTO();
		movieDTO.setMovieCode("M13");
		movieDTO.setMovieTitle("movie title");
		movieDTO.setMovieDirector("kalin");
		movieDTO.setMovieAge(10);
		movieDTO.setMovieGenre("action");
		movieDTO.setMovieStart(new Date(2019, 10,1));
		movieDTO.setMovieEnd(new Date(2019, 11, 1));
		MovieDAO.getInstance().addMovie(movieDTO);
	}

}
