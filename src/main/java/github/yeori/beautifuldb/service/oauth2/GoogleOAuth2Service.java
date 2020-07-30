package github.yeori.beautifuldb.service.oauth2;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.api.client.auth.oauth2.BearerToken;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.json.jackson2.JacksonFactory;

import github.yeori.beautifuldb.BeautDbException;
import github.yeori.beautifuldb.TypeMap;
import github.yeori.beautifuldb.dao.user.IOAuthAccountDao;
import github.yeori.beautifuldb.dao.user.IUserDao;
import github.yeori.beautifuldb.model.OAuthUser;
import github.yeori.beautifuldb.model.user.OAuthAccount;
import github.yeori.beautifuldb.model.user.User;

@Service
public class GoogleOAuth2Service {

	private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
	
	private HttpRequestFactory factory = HTTP_TRANSPORT.createRequestFactory();
	private JacksonFactory jackson = new JacksonFactory();
	
	@Autowired
	private IOAuthAccountDao oauthcDao;
	
	@Autowired
	IUserDao userDao;
	
	public void validate() {
		String accessKey = "ya29.a0AfH6SMB17OTSS_uO-CZnvGMXOqp-jBSCUdsqsAA7avk0OgNLnljhoh0PmyDfXcUcT6Nw7CO6UPXESsnWjxG9IFiQyrFd4q_uIJzyfPFn_aS6ydwqg1TFfkpktTnD0zfONalYXREv2eQgVQA85piheZjt7Jddf2e72Ls";
		Credential c = new Credential(BearerToken.authorizationHeaderAccessMethod()).setAccessToken(accessKey);
//		GoogleCredential c= new GoogleCredential().setAccessToken(accessKey);
		try {
			String scope = "https://www.googleapis.com/userinfo/v2/me";
//			String scope = "https://www.googleapis.com/auth/userinfo.email";
			// factory.buildGetRequest(new GenericUrl(encodedUrl))
			HttpRequest req = factory.buildGetRequest(new GenericUrl(scope));
			c.initialize(req);
			HttpResponse res = req.execute();
			System.out.printf("RESPONSE : %d\n", res.getStatusCode());
			System.out.printf("ENCODING : %s\n", res.getContentType());
			System.out.printf("BODY : %s\n", res.parseAsString());
			
			System.out.println(getUserInfo(accessKey));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public OAuthUser getUserInfo(String accessKey) {
		String entryPoint = "https://www.googleapis.com/userinfo/v2/me";
		// Credential c = new Credential(BearerToken.authorizationHeaderAccessMethod()).setAccessToken(accessKey);
		Credential c = new Credential(BearerToken.authorizationHeaderAccessMethod()).setAccessToken(accessKey);
		HttpRequest req;
		HttpResponse res = null;
		try {
			req = factory.buildGetRequest(new GenericUrl(entryPoint));
			req.setThrowExceptionOnExecuteError(false);
			req.setParser(new JsonObjectParser(jackson));
			c.initialize(req);
			res = req.execute();
			TypeMap data = res.parseAs(TypeMap.class);
			data.put("origin", "GOOGLE");
			return new OAuthUser(data);
		} catch (IOException e) {
			throw new BeautDbException(e, res.getStatusCode(), "LOGIN_REQUIRED");
		}
	}

	public  OAuthAccount findByEmail(String email) {
		OAuthAccount acc = oauthcDao.findByEmail(email);
		return acc;
	}

	public void insertAccount(OAuthAccount acc) {
		oauthcDao.save(acc);
	}

	
}
