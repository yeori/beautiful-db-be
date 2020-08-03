package github.yeori.dtomimic;

public class DtoMimicException extends RuntimeException {

	public DtoMimicException(String format, Object ... args) {
		super(String.format(format, args));
	}

}
