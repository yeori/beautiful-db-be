package github.yeori.beautifuldb.service.user;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import github.yeori.beautifuldb.dao.user.IOAuthAccountDao;
import github.yeori.beautifuldb.dao.user.IUserDao;
import github.yeori.beautifuldb.model.user.OAuthAccount;
import github.yeori.beautifuldb.model.user.User;
import github.yeori.beautifuldb.model.user.Users;

@Service
public class OAuthAccountService {

	@Autowired
	IUserDao userDao;
	
	@Autowired
	IOAuthAccountDao oauthAccountDao;
	
	@Transactional
	public OAuthAccount insertOAuthAccount(OAuthAccount acc) {
		User user = Users.randomUser();
		userDao.save(user);
		acc.setUserRef(user);
		oauthAccountDao.save(acc);
		return acc;
	}
}
