package github.yeori.beautifuldb.config;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class HttpParameterInjectionWrapper extends HttpServletRequestWrapper {

	private Map<String, String> mockParams;

	public HttpParameterInjectionWrapper(HttpServletRequest req, Map<String, String> params) {
		super(req);
		this.mockParams = new HashMap<>();
		if (mockParams != null) {
			this.mockParams.putAll(params);
		}
	}
	
	@Override
	public String getParameter(String name) {
		String value = this.mockParams.get(name);
		return value != null ? value : super.getParameter(name);
	}
}
