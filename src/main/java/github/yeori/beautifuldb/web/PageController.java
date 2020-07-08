package github.yeori.beautifuldb.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

	@GetMapping("/")
	public String pageMain() {
		System.out.println("here???");
		return "index";
	}
}
