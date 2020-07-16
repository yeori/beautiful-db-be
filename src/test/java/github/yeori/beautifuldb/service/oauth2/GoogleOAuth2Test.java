package github.yeori.beautifuldb.service.oauth2;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


class GoogleOAuth2Test {

	@Autowired GoogleOAuth2Service oauth = new GoogleOAuth2Service();
	
	@Test
	void test() {
		oauth.validate();
	}

}
