package feat.payment;

import feat.movie.MovieDAO;
import menu.MenuMapper;
import menu.MenuMapping;
import menu.MenuSelector;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

import static util.ConsoleUtil.*;

@MenuMapper
public class PaymentMapper {

	@MenuMapping("결제 관련")
	public void showPaymentMenu() throws IOException {
		while (true) {
			System.out.println("예약 관련 메뉴 입니다.");
			System.out.println("1. 영화 예약하기");
			System.out.println("2. 예약 수정하기");
			System.out.println("3. 예약 취소하기");
			System.out.println("4. 뒤로");

			String requestMenu = readString();

			if (StringUtils.equals(requestMenu, "뒤로")) {
				break;
			}

			MenuSelector.getInstance().select(requestMenu);
		}
	}

	@MenuMapping("결제 정보")
	public void getPaymentWays() {
		PaymentDAO.getInstance().selectPaymentWays().forEach(paymentDTO -> System.out.println(paymentDTO.toString()));
	}

	@MenuMapping("영화 예약하기")
	public void reserveMovie() throws IOException {
		printLn("영화 예매를 진행합니다.");
		printLn("현재 상영중인 영화");

		MovieDAO.getInstance().selectMovies().forEach(movieDTO -> System.out.println(movieDTO.toString()));
		String movieCode = readString("예매할 영화의 코드를 입력하세요");

		PaymentDAO.getInstance().selectPaymentWays().forEach(paymentDTO -> System.out.println(paymentDTO.toString()));
		// TODO: 방어로직 작성할 것
		String paymentWayCode = readString("예약 방식의 코드를 입력하세요");

		MoviePaymentDTO moviePaymentDTO = new MoviePaymentDTO();
		// TODO: Account 클래스 변경후 아래 코드 수정할 것
		moviePaymentDTO.setMyuserId("");
		moviePaymentDTO.setMovieCode(movieCode);
		moviePaymentDTO.setPaymentCode(paymentWayCode);
		moviePaymentDTO.setPaymentDate(Date.valueOf(LocalDate.now()));
	}

	@MenuMapping("예약 수정하기")
	public void modifyMovieReservation() throws IOException {
		printLn("예매한 영화를 수정합니다.");
		printLn("나의 예매 목록");
		// TODO: Account 수정 후 아래 코드 변경 할 것
		MoviePaymentDAO.getInstance().selectMyMoviePayments("id");
		// TODO: 방어로직 작성할 것
		MoviePaymentDTO moviePaymentDTO = new MoviePaymentDTO();

		String paymentCode = readString("수정할 예매 번호를 입력하세요");
		moviePaymentDTO.setPaymentCode(paymentCode);

		printLn(HORIZONTAL_RULE);
		MovieDAO.getInstance().selectMovies().forEach(movieDTO -> System.out.println(movieDTO.toString()));
		String movieCode = readString("어떤 영화로 바꾸시겠습니까? 바꿀 영화의 코드를 입력하세요");
		moviePaymentDTO.setMovieCode(movieCode);

		printLn(HORIZONTAL_RULE);
		PaymentDAO.getInstance().selectPaymentWays().forEach(paymentDTO -> paymentDTO.toString());
		String paymentWayCode = readString("결제 방법의 코드를 입력하세요");
		moviePaymentDTO.setPaymentCode(paymentCode);

		PaymentDAO.getInstance().updateMovieReservation(moviePaymentDTO);
	}

	@MenuMapping("예약 취소하기")
	public void removeMoviePayment() throws IOException {
		printLn("나의 예매 목록");
		// TODO: Account 수정 후 하기 코드 변경
		MoviePaymentDAO.getInstance().selectMyMoviePayments("id");
		int movieReservationCode = readInt("취소할 예매 코드: ");
		PaymentDAO.getInstance().deletePayment(movieReservationCode);
	}

	@MenuMapping("영화 결제 정보")
	public void getMoviePayments() {
		MoviePaymentDAO
			.getInstance()
			.selectMoviePayments()
			.forEach(moviePaymentDTO -> System.out.println(moviePaymentDTO.toString()));
	}

	@MenuMapping("나의 결제 내역")
	public void getMyMoviePayments() {
		// Account 수정 후 변경할 것
		MoviePaymentDAO
			.getInstance()
			.selectMyMoviePayments("id")
			.forEach(stringObjectMap -> {
				stringObjectMap.forEach((key, value) -> System.out.print(key + " : " + value + "\t"));
				System.out.println();
			});
	}
}
