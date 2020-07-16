package github.yeori.beautifuldb.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import github.yeori.beautifuldb.dao.IUserDao;
import github.yeori.beautifuldb.model.OAuthUser;
import github.yeori.beautifuldb.model.User;
import github.yeori.beautifuldb.model.UserWrapper;
import github.yeori.beautifuldb.service.oauth2.GoogleOAuth2Service;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	IUserDao userDao; 
	
	@Autowired
	GoogleOAuth2Service oauthService;
	
	public User findUser(String email) {
		return userDao.findByEmail(email);
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		return new UserWrapper(findUser(email));
	}
	
	public User checkExistingMember(String accessToken, String origin) {
		OAuthUser user = oauthService.getUserInfo(accessToken);
		return findUser(user.getEmail());
	}

	public User joinUser(String accessToken, String origin) {
		OAuthUser oauthUser = oauthService.getUserInfo(accessToken);
		User user = new User();
		user.setId(origin + ' ' + oauthUser.getId());
		user.setEmail(oauthUser.getEmail());
		user.setPassword(null);
		user.setConfirmedDate(LocalDateTime.now());
		return user;
	}
}
