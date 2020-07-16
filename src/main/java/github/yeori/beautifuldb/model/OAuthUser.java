package github.yeori.beautifuldb.model;

import github.yeori.beautifuldb.TypeMap;

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
}
