package feat.payment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import oracle.connect.ColumnName;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDTO {
	@ColumnName("PAYMENT_CODE")
	private String paymentCode;

	@ColumnName("PAYMENT_WAY")
	private String paymentWay;
}
