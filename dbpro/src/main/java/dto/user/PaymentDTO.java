package dto.user;

public class PaymentDTO implements DTO{
	public String PAYMENT_CODE;
	public String PAYMENT_WAY;

	public PaymentDTO(String pAYMENT_CODE, String pAYMENT_WAY) {
		PAYMENT_CODE = pAYMENT_CODE;
		PAYMENT_WAY = pAYMENT_WAY;
	}

	@Override
	public String toString() {
		return "PaymentDTO [PAYMENT_CODE=" + PAYMENT_CODE + ", PAYMENT_WAY=" + PAYMENT_WAY + "]";
	}
}
