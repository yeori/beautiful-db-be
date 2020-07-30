package github.yeori.beautifuldb.service.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.Rollback;

import github.yeori.beautifuldb.config.AopConfiguration;
import github.yeori.beautifuldb.dao.user.IOAuthAccountDao;
import github.yeori.beautifuldb.dao.user.IUserDao;
import github.yeori.beautifuldb.model.user.OAuthAccount;
import github.yeori.beautifuldb.service.user.OAuthAccountService;


@AutoConfigureTestDatabase(replace = Replace.NONE)
@DataJpaTest(showSql = true)
// @Import({ServiceSpecific.class, JpaRepositoriesAutoConfiguration.class})
public class OAuthAccountServiceTest {

	@Autowired
	IUserDao userDao;
	
	@Autowired
	IOAuthAccountDao accDao;
	
	@Test
	@Rollback(false)
	void test_insert_oauth_account() {
		OAuthAccountService accService = new OAuthAccountService();
		accService.userDao = userDao;
		accService.oauthAccountDao = accDao;
		OAuthAccount acc = new OAuthAccount();
		acc.setEmail("chmin.seo@gmail.com");
		acc.setId("938388");
		acc.setOrigin("GOOGLE");
		accService.insertOAuthAccount(acc);
		
	}

}
