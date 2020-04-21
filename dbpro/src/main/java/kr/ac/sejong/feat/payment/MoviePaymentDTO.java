package kr.ac.sejong.feat.payment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import kr.ac.sejong.config.ColumnName;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MoviePaymentDTO {
	@ColumnName("MOVIE_PAYMENT_CODE")
	private int moviePaymentCode;

	@ColumnName("MYUSER_ID")
	private String myuserId;

	@ColumnName("MOVIE_CODE")
	private String movieCode;

	@ColumnName("PAYMENT_CODE")
	private String paymentCode;

	@ColumnName("PAYMENT_DATE")
	private Date paymentDate;
}
