package github.yeori.beautifuldb.model.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import github.yeori.beautifuldb.dao.user.IOAuthAccountDao;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long seq;
	
	@Column(updatable = false)
	private String id;
	
	private String password;
	
	@Transient
	private OAuthAccount loginAccount;
	
	public User() {}
	public User(String id, String password) {
		this.id = id;
		this.password = password;
	}
	public Long getSeq() {
		return seq;
	}
	public void setSeq(Long seq) {
		this.seq = seq;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setLoginAccount(OAuthAccount acc) {
		this.loginAccount = acc;
	}
	public OAuthAccount getLoginAccount() {
		return this.loginAccount;
	}

	@Override
	public String toString() {
		return "User [seq=" + seq + ", id=" + id + ", password=" + password + "]";
	}

	
}
