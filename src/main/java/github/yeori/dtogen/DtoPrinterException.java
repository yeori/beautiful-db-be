package github.yeori.dtogen;

public class DtoPrinterException extends RuntimeException {

	public DtoPrinterException(String format, Object ... args) {
		super(String.format(format, args));
	}

}
