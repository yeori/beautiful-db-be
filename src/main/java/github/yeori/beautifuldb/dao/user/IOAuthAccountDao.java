package github.yeori.beautifuldb.dao.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import github.yeori.beautifuldb.model.user.OAuthAccount;

@Repository
public interface IOAuthAccountDao extends JpaRepository<OAuthAccount, Long> {
	
	OAuthAccount findByEmail(String email);
	
}
