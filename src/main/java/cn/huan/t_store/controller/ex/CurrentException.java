package cn.huan.t_store.controller.ex;

public class CurrentException extends RuntimeException {

	
	private static final long serialVersionUID = 6938788645644558976L;

	public CurrentException() {
		super();
	}

	public CurrentException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public CurrentException(String message, Throwable cause) {
		super(message, cause);
	}

	public CurrentException(String message) {
		super(message);
	}

	public CurrentException(Throwable cause) {
		super(cause);
	}

	
}
