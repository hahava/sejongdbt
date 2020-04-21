package kr.ac.sejong.feat.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import kr.ac.sejong.config.ColumnName;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyuserDTO {
	@ColumnName("MYUSER_ID")
	private String myuserId;

	@ColumnName("MYUSER_NAME")
	private String myuserName;

	@ColumnName("MYUSER_PW")
	private String myuserPw;

	@ColumnName("MYUSER_BIRTH")
	private Date myuserBirth;

	@ColumnName("MYUSER_PHONE")
	private String myuserPhone;

	@ColumnName("MYUSER_EMAIL")
	private String myuserEmail;
}
