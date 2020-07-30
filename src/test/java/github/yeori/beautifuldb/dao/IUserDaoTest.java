package github.yeori.beautifuldb.dao;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import github.yeori.beautifuldb.dao.user.IOAuthAccountDao;
import github.yeori.beautifuldb.dao.user.IUserDao;
import github.yeori.beautifuldb.model.user.OAuthAccount;
import github.yeori.beautifuldb.model.user.User;

// @SpringBootTest
@DataJpaTest(showSql = true)
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class IUserDaoTest {

	@Autowired IUserDao userDao;
	@Autowired IOAuthAccountDao accDao;
	
	@Test
	@Rollback(false)
	void test_insert_user() {
		User user = new User();
		user.setId("chmin");
		user.setPassword("2333");
//		user.setEmail("goekk.seo@gmail.com");
//		user.setOrigin("GOOGLE");
//		user.setJoinDate(LocalDateTime.now());
		userDao.save(user);
		
		user.setPassword("2323");
	}
	
	@Test
	@Rollback(false)
	void test_insert_userAccount(@Autowired IUserDao userDao) {
		
		User user = new User();
		user.setId("mock-id-group");
		user.setPassword("*******");
		
		userDao.save(user);
		
		OAuthAccount acc = new OAuthAccount();
		acc.setId("google-3939399");
		acc.setEmail("yeori.seo@gmail.com");
		acc.setOrigin("GOOGLE");
		acc.setUserRef(user);
		
		accDao.save(acc);
		
	}

}
