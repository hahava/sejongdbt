package kr.ac.sejong.auth;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
	private String id;
	private AuthLevel level;
}
