package kr.ac.sejong.feat.paymentstatics;

import kr.ac.sejong.menu.MenuMapper;
import kr.ac.sejong.menu.MenuMapping;

@MenuMapper
public class PaymentStatisticsMapper {

	@MenuMapping("결제 통계 정보")
	public void getPaymentStatics() {
		PaymentStaticsDAO.getInstance().selectPaymentStatics()
			.forEach(paymentStaticsDTO -> System.out.println(paymentStaticsDTO));
	}

}
