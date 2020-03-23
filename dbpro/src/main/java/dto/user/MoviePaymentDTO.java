package dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import oracle.connect.ColumnName;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MoviePaymentDTO {
	@ColumnName("MOVIE_PAYMENT_CODE")
	public int moviePaymentCode;

	@ColumnName("MYUSER_ID")
	public String myuserId;

	@ColumnName("MOVIE_CODE")
	public String movieCode;

	@ColumnName("PAYMENT_CODE")
	public String paymentCode;

	@ColumnName("PAYMENT_DATE")
	public Date paymentDate;
}
