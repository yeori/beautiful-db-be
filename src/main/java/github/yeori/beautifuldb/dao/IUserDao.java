package github.yeori.beautifuldb.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import github.yeori.beautifuldb.model.User;
@Repository
public interface IUserDao extends JpaRepository<User, Long>{

	public User findByEmail(String email);
}
