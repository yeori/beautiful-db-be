package github.yeori.beautifuldb.config.parts;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import github.yeori.beautifuldb.BeautDbException;
import github.yeori.beautifuldb.TypeMap;
import github.yeori.beautifuldb.Util;
import github.yeori.beautifuldb.config.HttpParameterInjectionWrapper;
import github.yeori.beautifuldb.service.token.JwtService;

public class JwtTokenDecoingFilter extends OncePerRequestFilter {

		private JwtService jwtService;
		private AuthenticationManager authManager;

		public JwtTokenDecoingFilter(JwtService jwtService, AuthenticationManager authManager) {
			this.jwtService = jwtService;
			this.authManager = authManager;
		}

		@Override
		protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res,
				FilterChain chain) throws ServletException, IOException {
//			HttpServletRequest req = (HttpServletRequest)request;
			System.out.println("[JtwTokenDecodingFilter] " + req.getClass().getName());
			
			if ("/loginByOAuth".equals(req.getRequestURI())) {
				// req.setAttribute("__spring_security_filterSecurityInterceptor_filterApplied", true);
				chain.doFilter(req, res);
				return;
			}
			
			String jwtToken = Util.parseBearer(req.getHeader("Authorization"));
			if (jwtToken == null) {
				chain.doFilter(req, res);
				return;
//				throw new BeautDbException(401, "LOGIN_REQUIRED");
			}
			
			try {
				TypeMap body = jwtService.decodeToken(jwtToken);
				Map<String, String> param =  new HashMap<>();
				param.put("username", body.getStr("sub"));
				
				UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(body.getStr("sub"), "");
				Authentication authentication = this.authManager.authenticate(token);
				
				SecurityContextHolder.getContext().setAuthentication(authentication);
				HttpParameterInjectionWrapper wrapper = new HttpParameterInjectionWrapper(req, param);
				
				chain.doFilter(wrapper, res);
			} catch (BeautDbException e) {
				req.setAttribute("BEAUTIFULDB_APP_EXCEPTION", e);
				throw e;
			}
			
		}
	 }