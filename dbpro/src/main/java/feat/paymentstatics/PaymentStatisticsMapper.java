package feat.paymentstatics;

import menu.MenuMapper;
import menu.MenuMapping;

@MenuMapper
public class PaymentStatisticsMapper {

	@MenuMapping("결제 통계 정보")
	public void getPaymentStatics() {
		PaymentStaticsDAO.getInstance().selectPaymentStatics()
			.forEach(paymentStaticsDTO -> System.out.println(paymentStaticsDTO));
	}

}
