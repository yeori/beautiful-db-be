package github.yeori.beautifuldb;

public class BeautDbException extends RuntimeException {

	private static final long serialVersionUID = -8368426053389993638L;
	private String errorCode;
	private String detailMessage;
	private int responseCode = 400;
	
	public BeautDbException(Throwable e, int resCode, String errorCode) {
		super(errorCode, e);
		this.errorCode = errorCode;
		this.responseCode = resCode;
	}
	public BeautDbException(int resCode, String errorCode, String format, Object ... args) {
		super(errorCode);
		this.responseCode = resCode;
		this.errorCode = errorCode;
		this.detailMessage = String.format(format, args);
	}
	public BeautDbException(int resCode, String errorCode) {
		super(errorCode);
		this.responseCode = resCode;
		this.errorCode = errorCode;
		this.detailMessage = errorCode;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public int getResponseCode() {
		return responseCode;
	}
	public String getDetailMessage() {
		return detailMessage;
	}
	public String asJson() {
		String format = "{\"success\": false, \"cause\": \"@cause\", \"desc\": \"@desc\"}";
		return format
			.replace("@cause", this.errorCode)
			.replace("@desc", getDetailMessage());
	}
}
