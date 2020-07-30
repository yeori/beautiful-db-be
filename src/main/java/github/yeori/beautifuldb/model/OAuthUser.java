package github.yeori.beautifuldb.model;

import github.yeori.beautifuldb.TypeMap;
import github.yeori.beautifuldb.model.user.OAuthAccount;

public class OAuthUser {

	final TypeMap res;
	
	public OAuthUser(TypeMap res) {
		this.res = res;
	}
	
	public String getEmail() {
		return res.getStr("email");
	}
	
	public String getName() {
		return res.getStr("name");
	}
	
	public String getAccessToken() {
		return res.getStr("access_token");
	}
	
	public String getOrigin() {
		return res.getStr("origin");
	}

	public String getId() {
		return res.getStr("id");
	}

	public OAuthAccount toOAuthAccount() {
		OAuthAccount acc = new OAuthAccount();
		acc.setId(getId());
		acc.setEmail(getEmail());
		acc.setOrigin(getOrigin());
		return acc;
	}
}
