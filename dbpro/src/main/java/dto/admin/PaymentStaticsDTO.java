package dto.admin;

import java.sql.Date;

public class PaymentStaticsDTO {
	public Date PAYMENT_DATE;
	public int PAYMENT_SUM;
	public int PAYMENT_COUNT;
	public int MYUSER_COUNT;

	@Override
	public String toString() {
		return "PaymentStaticsDTO [PAYMENT_DATE=" + PAYMENT_DATE + ", PAYMENT_SUM=" + PAYMENT_SUM + ", PAYMENT_COUNT="
				+ PAYMENT_COUNT + ", MYUSER_COUNT=" + MYUSER_COUNT + "]";
	}

	public PaymentStaticsDTO(Date pAYMENT_DATE, int pAYMENT_SUM, int pAYMENT_COUNT, int mYUSER_COUNT) {
		PAYMENT_DATE = pAYMENT_DATE;
		PAYMENT_SUM = pAYMENT_SUM;
		PAYMENT_COUNT = pAYMENT_COUNT;
		MYUSER_COUNT = mYUSER_COUNT;
	}

}
