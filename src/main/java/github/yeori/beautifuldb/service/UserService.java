package github.yeori.beautifuldb.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import github.yeori.beautifuldb.TypeMap;
import github.yeori.beautifuldb.dao.user.IUserDao;
import github.yeori.beautifuldb.model.OAuthUser;
import github.yeori.beautifuldb.model.UserWrapper;
import github.yeori.beautifuldb.model.user.OAuthAccount;
import github.yeori.beautifuldb.model.user.User;
import github.yeori.beautifuldb.service.oauth2.GoogleOAuth2Service;
import github.yeori.beautifuldb.service.token.JwtService;
import github.yeori.dtomimic.DtoMimic;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	IUserDao userDao; 
	
	@Autowired
	GoogleOAuth2Service oauthService;
	
	@Autowired
	JwtService jwtSerivce;

	@Autowired
	DtoMimic dtoMimicker;
	
	@Transactional(readOnly = true)
	public User findUser(String email) {
		OAuthAccount acc = oauthService.findByEmail(email);
		if (acc == null) {
			return null;
		}
		User user = acc.getUserRef();
		user.setLoginAccount(acc);
		return dtoMimicker.mimic(user, "password", "loginAccount.userRef");
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		return new UserWrapper(findUser(email));
	}
	
	public TypeMap login(String accessToken, String origin) {
		OAuthUser oAuthUser = oauthService.getUserInfo(accessToken);
		User user = findUser(oAuthUser.getEmail());
		TypeMap res = TypeMap.with("user", user);
		if (user != null) {
			String jwtToken = jwtSerivce.createToken(user);
			res.put("token", jwtToken);
		}
		return res;
	}

	public User joinUser(String accessToken, String origin) {
		OAuthUser oauthUser = oauthService.getUserInfo(accessToken);
		User user = new User();
		user.setId(origin + ' ' + oauthUser.getId());
//		user.setEmail(oauthUser.getEmail());
		user.setPassword(UUID.randomUUID().toString());
//		user.setConfirmedDate(LocalDateTime.now());
		userDao.save(user);
		OAuthAccount acc = oauthUser.toOAuthAccount();
		acc.setUserRef(user);
		oauthService.insertAccount(acc);
		user.setLoginAccount(acc);
		return user;
	}
}
