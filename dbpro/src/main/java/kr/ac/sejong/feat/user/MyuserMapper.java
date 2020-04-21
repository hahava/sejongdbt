package kr.ac.sejong.feat.user;

import kr.ac.sejong.feat.snack.MyuserSnackOrderDAO;
import kr.ac.sejong.feat.movie.MovieDAO;
import kr.ac.sejong.menu.MenuMapper;
import kr.ac.sejong.menu.MenuMapping;
import kr.ac.sejong.menu.MenuSelector;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;

import static kr.ac.sejong.util.ConsoleUtil.*;

@MenuMapper
public class MyuserMapper {

	@MenuMapping("유저 정보")
	public void getUsers() {
		MyuserDAO.getInstance().selectUsers().forEach(myuserDTO -> System.out.println(myuserDTO.toString()));
	}

	@MenuMapping("우수회원")
	public void showBestMemberMenu() throws IOException {

		while (true) {
			System.out.println("이달의 우수회원을 선출합니다.");
			System.out.println("가장 많이 본 회원 1명 선출");
			System.out.println("(영화)범위 지정 후 여러명 선출");
			System.out.println("스낵에 돈을 가장 많이 쓴 회원 선출");
			System.out.println("(스낵)범위 지정후 여러명 선출");

			String requestMenu = readString();

			if (StringUtils.equals(requestMenu, "뒤로")) {
				break;
			}

			MenuSelector.getInstance().select(requestMenu);
		}
	}

	@MenuMapping("가장 많이 본 회원 1명 선출")
	public void getPersonWhoBookedTheMostMovies() {
		MovieDAO
			.getInstance()
			.selectPersonWhoBookedTheMostMovies()
			.forEach((key, value) -> System.out.println(key + ":" + value));
	}

	@MenuMapping("(영화)범위 지정 후 여러명 선출")
	public void getMovieReservationCountOfPerson() throws IOException {
		int reservationCount = readInt("범위를 지정하세요.입력한 숫자 이상 영화를 본 회원들을 출력합니다.");
		MovieDAO
			.getInstance()
			.selectMovieReservationCountOfPerson(reservationCount)
			.forEach(stringObjectMap -> {
				System.out.print("[ ");
				stringObjectMap.forEach((key, value) -> {
					System.out.print(key + " : " + value + "\t");
				});
				System.out.println(" ]");
			});
	}

	@MenuMapping("스낵에 돈을 가장 많이 쓴 회원 선출")
	public void getMostOrderedMember() throws IOException {
		printLn("가장 스낵에 돈을 많이 투자한 회원을 출력합니다.");
		MyuserSnackOrderDAO.
			getInstance()
			.selectMostOrderedMember()
			.forEach((key, value) -> System.out.println(key + " : " + value));
	}

	@MenuMapping("(스낵)범위 지정후 여러명 선출")
	public void getMinPriceOrders() throws IOException {
		int minPrice = readInt("스낵에 돈을 많이 투자한 회원을 출력합니다. 범위를 지정하세요.");
		MyuserSnackOrderDAO
			.getInstance()
			.selectMinPriceOrders(minPrice)
			.forEach(stringObjectMap -> {
				stringObjectMap.forEach((key, value) -> {
					System.out.print(key + " : " + value + "\t");
				});
				System.out.println();
			});
		;
	}

}
