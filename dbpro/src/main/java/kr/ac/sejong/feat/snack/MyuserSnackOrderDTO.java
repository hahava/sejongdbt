package kr.ac.sejong.feat.snack;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import kr.ac.sejong.config.ColumnName;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MyuserSnackOrderDTO {
	@ColumnName("ORDER_NUM")
	private int orderNum;

	@ColumnName("MYUSER_ID")
	private String myuserId;

	@ColumnName("SNACK_CODE")
	private String snackCode;

	@ColumnName("ORDER_DATE")
	private Date orderDate;
}
