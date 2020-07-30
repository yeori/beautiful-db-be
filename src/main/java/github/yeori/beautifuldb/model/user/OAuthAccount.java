package github.yeori.beautifuldb.model.user;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class OAuthAccount {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long seq;

	@Column(updatable = false)
	private String id;
	
	@Column(updatable = false)
	private String email;
	
	@Column(updatable = false, insertable = false)
	private LocalDateTime joinDate;
	
	@Column(updatable = false)
	private String origin;
	
	@ManyToOne
	@JoinColumn(name = "user_ref")
	private User userRef;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Long getSeq() {
		return seq;
	}
	public void setSeq(Long seq) {
		this.seq = seq;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public LocalDateTime getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(LocalDateTime joinDate) {
		this.joinDate = joinDate;
	}
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	public User getUserRef() {
		return userRef;
	}
	public void setUserRef(User userRef) {
		this.userRef = userRef;
	}
	@Override
	public String toString() {
		return "OAuthAccount [seq=" + seq + ", email=" + email + ", joinDate=" + joinDate + ", origin=" + origin
				+ ", userRef=" + userRef + "]";
	}
}
