package feat.actor;

import menu.MenuMapper;
import menu.MenuMapping;
import util.ConsoleUtil;

import java.io.IOException;
import java.util.List;

@MenuMapper
public class ActorMapper {

	@MenuMapping("배우 정보")
	public void getActors() {
		List<ActorDTO> actors = ActorDAO.getInstance().selectActors();
	}

	@MenuMapping("영화에 출연한 배우 정보")
	public void getActorByMovieTitle() throws IOException {
		String movieTitle = ConsoleUtil.readString("특정 영화에 출연한 배우를 검색합니다. 영화 명을 입력하세요.");
		List<ActorDTO> actors = ActorDAO.getInstance().selectActorByMovieTitle(movieTitle);
		if (actors.size() == 0) {
			System.out.println("영화정보가 없거나 배우정보가 존재하지 않습니다.");
		} else {
			actors.stream()
				.forEach(actorDTO -> System.out.println("배우명 : " + actorDTO.getActorName() + "\t" + "성별 : " + actorDTO
					.getActorGender() + " \t " + "생년월일 : " + actorDTO.getActorBirth()));
		}
	}

	@MenuMapping("영화에 출연한 배우 정보")
	public void getMovieActors() {
		MovieActorDAO
			.getInstance()
			.selectMovieActors()
			.forEach(movieActorDTO -> System.out.println(movieActorDTO.toString()));
	}

}
