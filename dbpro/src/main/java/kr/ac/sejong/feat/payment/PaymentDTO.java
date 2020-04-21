package kr.ac.sejong.feat.payment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import kr.ac.sejong.config.ColumnName;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDTO {
	@ColumnName("PAYMENT_CODE")
	private String paymentCode;

	@ColumnName("PAYMENT_WAY")
	private String paymentWay;
}
