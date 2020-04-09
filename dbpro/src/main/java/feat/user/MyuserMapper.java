package feat.user;

import menu.MenuMapper;
import menu.MenuMapping;

@MenuMapper
public class MyuserMapper {

	@MenuMapping("유저 정보")
	public void getUsers() {
		System.out.println("유저 정보");
	}

}
