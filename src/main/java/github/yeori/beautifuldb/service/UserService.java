package github.yeori.beautifuldb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import github.yeori.beautifuldb.dao.IUserDao;
import github.yeori.beautifuldb.model.User;
import github.yeori.beautifuldb.model.UserWrapper;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	IUserDao userDao; 
	
	public User findUser(String email) {
		return userDao.findByEmail(email);
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		return new UserWrapper(findUser(email));
	}
}
