package github.yeori.beautifuldb.model.user;

import java.util.UUID;

public class Users {

	public static User randomUser() {
		String randomId = UUID.randomUUID().toString(); // len: 36
		String randomPass = UUID.randomUUID().toString(); // len: 36
		return new User(randomId, randomPass);
	}

}
