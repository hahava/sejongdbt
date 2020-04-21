package kr.ac.sejong.feat.snack;

import kr.ac.sejong.menu.MenuMapper;
import kr.ac.sejong.menu.MenuMapping;

@MenuMapper
public class SnackMapper {
	@MenuMapping("간식 종류")
	public void getSnacks() {
		SnackInfoDAO.getInstance().selectSnacks().forEach(snackInfoDTO -> System.out.println(snackInfoDTO.toString()));
	}

	@MenuMapping("회원들이 주문한 간식 종류")
	public void getOrderedSnacks() {
		MyuserSnackOrderDAO
			.getInstance()
			.selectOrderedSnacks()
			.forEach(myuserSnackOrderDTO -> System.out.println(myuserSnackOrderDTO.toString()));
	}

	@MenuMapping("나의 스낵주문 내역")
	public void getMySnackOrders() {
		// TODO: Account 수정 후 변경 할 것
		MyuserSnackOrderDAO
			.getInstance()
			.selectMySnackOrders("id")
			.forEach(stringObjectMap -> {
				stringObjectMap.forEach((key, value) -> {
					System.out.print(key + " : " + value + "\t");
				});
				System.out.println();
			});
	}
}
