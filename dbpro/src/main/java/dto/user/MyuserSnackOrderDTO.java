package dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import oracle.connect.ColumnName;

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
