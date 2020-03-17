package dto.admin;

import oracle.connect.ColumnName;

public class MovieActorDTO implements DTO{

	@ColumnName("ACTOR_CODE")
	private String actorCode;
	@ColumnName("MOVIE_CODE")
	private String movieCode;
	@ColumnName("ACTOR_ROLE")
	private String actorRole;

	@Override
	public String toString() {
		return "MovieActorDTO [ACTOR_CODE=" + actorCode + ", MOVIE_CODE=" + movieCode + ", ACTOR_ROLE=" + actorRole
				+ "]";
	}

	public MovieActorDTO() {
	}

	public MovieActorDTO(String aCTOR_CODE, String mOVIE_CODE, String aCTOR_ROLE) {
		super();
		actorCode = aCTOR_CODE;
		movieCode = mOVIE_CODE;
		actorRole = aCTOR_ROLE;
	}

}
