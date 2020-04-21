package kr.ac.sejong.feat.advertisement;

import kr.ac.sejong.menu.MenuMapper;
import kr.ac.sejong.menu.MenuMapping;

import java.util.List;

@MenuMapper
public class AdMapper {

	@MenuMapping("광고 정보")
	public void getADs() {
		List<AdDTO> advertisements = AdDAO.getInstance().selectAdvertisements();
		advertisements.forEach(adDTO -> System.out.println(adDTO.toString()));
	}

	@MenuMapping("영화에 삽입 된 정보")
	public void getMovieAds() {
		MovieAdDAO.getInstance().selectMovieADs().forEach(movieAdDTO -> System.out.println(movieAdDTO.toString()));
	}

}
