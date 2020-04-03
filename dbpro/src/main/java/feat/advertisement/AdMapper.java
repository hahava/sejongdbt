package feat.advertisement;

import menu.MenuMapper;
import menu.MenuMapping;

import java.util.List;

@MenuMapper
public class AdMapper {

	@MenuMapping("광고 정보")
	public void getADs() {
		List<AdDTO> advertisements = AdDAO.getInstance().selectAdvertisements();
		advertisements.forEach(adDTO -> System.out.println(adDTO.toString()));
	}

}
