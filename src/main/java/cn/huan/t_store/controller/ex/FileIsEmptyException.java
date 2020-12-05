package cn.huan.t_store.controller.ex;

public class FileIsEmptyException extends CurrentException {

	private static final long serialVersionUID = -725710862560578881L;

	public FileIsEmptyException() {
		super();
	}

	public FileIsEmptyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public FileIsEmptyException(String message, Throwable cause) {
		super(message, cause);
	}

	public FileIsEmptyException(String message) {
		super(message);
	}

	public FileIsEmptyException(Throwable cause) {
		super(cause);
	}

	
}
