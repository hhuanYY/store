package cn.huan.t_store.service.ex;

/**
 * 插入数据异常
 */
public class CartNotException extends ServiceException {


	/**
	 * 
	 */
	private static final long serialVersionUID = 2723623196394839883L;

	/**
	 * 
	 */

	public CartNotException() {
		super();
	}

	public CartNotException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public CartNotException(String message, Throwable cause) {
		super(message, cause);
	}

	public CartNotException(String message) {
		super(message);
	}

	public CartNotException(Throwable cause) {
		super(cause);
	}

}
