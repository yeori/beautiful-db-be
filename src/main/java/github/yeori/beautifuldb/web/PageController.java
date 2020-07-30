package github.yeori.beautifuldb.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import github.yeori.beautifuldb.Res;

@Controller
public class PageController {

	@GetMapping("/")
	public String pageMain() {
		System.out.println("here???");
		return "index";
	}
	
	@GetMapping("/login")
	public String pageLogin() {
		return "login";
	}
	@GetMapping("/login/success")
	@ResponseBody
	public Object pageMy(HttpServletRequest req) {
		System.out.println(req.getAttribute("_csrf"));
		return Res.success("token", true);
	}
}
