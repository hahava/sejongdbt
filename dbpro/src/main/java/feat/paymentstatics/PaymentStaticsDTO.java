package feat.paymentstatics;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import config.ColumnName;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentStaticsDTO {
	@ColumnName("PAYMENT_DATE")
	private Date paymentDate;

	@ColumnName("PAYMENT_SUM")
	private int paymentSum;

	@ColumnName("PAYMENT_COUNT")
	private int paymentCount;

	@ColumnName("MYUSER_COUNT")
	private int myuserCount;
}
