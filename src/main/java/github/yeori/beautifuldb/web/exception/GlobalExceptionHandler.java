package github.yeori.beautifuldb.web.exception;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import github.yeori.beautifuldb.BeautDbException;
import github.yeori.beautifuldb.Res;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(BeautDbException.class)
	public Object handleApplicationException(BeautDbException e) {
		int resCode = e.getResponseCode();
		HttpStatus status = codeToStatus(resCode);
		
		Map<String, Object> body = Res.failure(
				"cause", e.getErrorCode(),
				"desc", e.getMessage());
		return new ResponseEntity<Map<String, Object>>(body, status);
	}
	
	private HttpStatus codeToStatus(int resCode) {
        for(HttpStatus status : HttpStatus.values()) {
            if (status.value() == resCode) {
                return status;
            }
        }
        return HttpStatus.BAD_REQUEST;
    }
}
