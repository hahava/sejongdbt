package dto.admin;

import dto.user.DTO;

public class MovieActorDTO implements DTO{

	public String ACTOR_CODE;
	public String MOVIE_CODE;
	public String ACTOR_ROLE;

	@Override
	public String toString() {
		return "MovieActorDTO [ACTOR_CODE=" + ACTOR_CODE + ", MOVIE_CODE=" + MOVIE_CODE + ", ACTOR_ROLE=" + ACTOR_ROLE
				+ "]";
	}

	public MovieActorDTO(String aCTOR_CODE, String mOVIE_CODE, String aCTOR_ROLE) {
		super();
		ACTOR_CODE = aCTOR_CODE;
		MOVIE_CODE = mOVIE_CODE;
		ACTOR_ROLE = aCTOR_ROLE;
	}

}
