package github.yeori.beautifuldb.aop;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.csrf.DefaultCsrfToken;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import github.yeori.beautifuldb.BeautDbException;

@Aspect
@Component
public class CsrfTokenAppender {

	private static final Logger logger = LoggerFactory.getLogger(CsrfTokenAppender.class);
	
	@Pointcut("@annotation(org.springframework.web.bind.annotation.GetMapping)")
	public void getMapping() {}
	
	@Pointcut("@annotation(org.springframework.web.bind.annotation.PostMapping)")
	public void post() {}
	
	@Pointcut("@annotation(org.springframework.web.bind.annotation.PutMapping)")
	public void put() {}
	
	@Pointcut("@annotation(org.springframework.web.bind.annotation.DeleteMapping)")
	public void del() {}
	
	@Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
	public void requestMapping() {}
	
//	@Around("within(github.yeori.beautifuldb.web.*)")
	@Around("getMapping() || post() || put() || del()")
	public Object handleResponse(ProceedingJoinPoint jpoint) {
		return handle(jpoint);
	}
	
	@Around("requestMapping()")
	public Object handleRequestMapping(ProceedingJoinPoint jpoint) {
		return handle(jpoint);
	}
	
	private Object handle(ProceedingJoinPoint jpoint) {
		Object res = null;
		try {
			res = jpoint.proceed();
			logger.info("[AOP] returning " + res.getClass().getName());
			if (res instanceof Map) {
				appendCsrfToken(Map.class.cast(res));
			}
			return res;
		} catch (Throwable e) {
			throw new BeautDbException(e, 500, "SERVER_ERROR");
		}
	}

	private void appendCsrfToken(Map<String, Object> res) {
		/*
		 * expected csrf token is published into request with name '_csrf' by CsrfTokenRepository from SecurityConfig.class
		 */
		HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		DefaultCsrfToken csrfToken = (DefaultCsrfToken) req.getAttribute("_csrf");
		if (csrfToken != null) {
			logger.info("[AOP] attaching csrf token: " + csrfToken.getToken());
			res.put(csrfToken.getParameterName(), csrfToken.getToken());
		}
		
	}
}
