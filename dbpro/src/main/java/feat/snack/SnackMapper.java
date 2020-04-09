package feat.snack;

import menu.MenuMapper;
import menu.MenuMapping;

@MenuMapper
public class SnackMapper {
	@MenuMapping("간식 종류")
	public void getSnacks() {
		SnackInfoDAO.getInstance().selectSnacks().forEach(snackInfoDTO -> System.out.println(snackInfoDTO.toString()));
	}
}
