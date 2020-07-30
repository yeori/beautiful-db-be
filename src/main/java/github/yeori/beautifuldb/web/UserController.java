package github.yeori.beautifuldb.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.DefaultCsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import github.yeori.beautifuldb.Res;
import github.yeori.beautifuldb.TypeMap;
import github.yeori.beautifuldb.model.user.User;
import github.yeori.beautifuldb.service.UserService;

@RestController
public class UserController {

	static Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired CsrfTokenRepository csrfRepo;
	
	@Autowired
	UserService userService;
	
	@PostMapping("/oauth/login")
	public Object login(@RequestBody TypeMap req) {
		logger.info(req.toString());
		return Res.success("req", req);
	}
	@GetMapping("/ready")
	public Object ready(HttpServletRequest req, HttpServletResponse res) {
		DefaultCsrfToken token = (DefaultCsrfToken) req.getAttribute("_csrf");
		String csrfValue = null;
		if(token != null) {
//			System.out.println("[CSRF] " + token.getToken());
			csrfValue = token.getToken();
		}
		
		return Res.success("ready", true, "_csrf", csrfValue);
	}
	@PostMapping("/member")
	public Object checkMember(@RequestBody TypeMap req) {
		User user = userService.checkExistingMember(req.getStr("accessToken"), req.getStr("origin"));
		
		return Res.with(user != null, "user", user);
	}
	@PostMapping("/join")
	public Object join(@RequestBody TypeMap req) {
		String origin = req.getStr("origin");
		String accessToken = req.getStr("accessToken");
		User user = userService.joinUser(accessToken, origin);
		return Res.success("origin", origin, "user", user);
	}
}
