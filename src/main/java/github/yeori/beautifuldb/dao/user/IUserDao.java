package github.yeori.beautifuldb.dao.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import github.yeori.beautifuldb.model.user.User;
@Repository
public interface IUserDao extends JpaRepository<User, Long>{

//	public User findByEmail(String email);
	
//	public void insertUser(User user);
}
