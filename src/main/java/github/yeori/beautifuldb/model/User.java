package github.yeori.beautifuldb.model;

import java.time.LocalDateTime;

public class User {

	private Long seq;
	private String userId;
	private String email;
	private String password;
	/**
	 * 가입일
	 */
	private LocalDateTime joinDate;
	/**
	 * 이메일 확인 시간
	 */
	private LocalDateTime confirmedDate;
}
