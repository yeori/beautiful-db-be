package github.yeori.beautifuldb.model;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import github.yeori.beautifuldb.model.user.User;

public class UserWrapper implements UserDetails, CredentialsContainer {
	
	final private static List<GrantedAuthority> USER_ROLES = Arrays.asList(new NormalRole());
	final private static List<GrantedAuthority> ADMIN_ROLES = Arrays.asList(USER_ROLES.get(0) , new AdminRole());
	
	private User target;
	private boolean admin;
	public UserWrapper(User user) {
		this.target = user;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return admin ? ADMIN_ROLES : USER_ROLES;
	}

	@Override
	public String getPassword() {
		return target.getPassword();
	}

	@Override
	public String getUsername() {
		// FIXME OAuthAccount로 변경해야함
		return null; //target.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
	public final static class NormalRole implements GrantedAuthority {
		
		@Override
		public String getAuthority() {
			return "USER";
		}
	}
	
	public final static class AdminRole implements GrantedAuthority {
		
		@Override
		public String getAuthority() {
			return "ADMIN";
		}
	}

	@Override
	public void eraseCredentials() {
		target.setPassword("[E*R*A*S*E*D]");
	}
}
