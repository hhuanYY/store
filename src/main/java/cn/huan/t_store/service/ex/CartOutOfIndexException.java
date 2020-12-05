package cn.huan.t_store.service.ex;

/**
 * 插入数据异常
 */
public class CartOutOfIndexException extends ServiceException {



	/**
	 * 
	 */
	private static final long serialVersionUID = 4223561912055200710L;

	public CartOutOfIndexException() {
		super();
	}

	public CartOutOfIndexException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public CartOutOfIndexException(String message, Throwable cause) {
		super(message, cause);
	}

	public CartOutOfIndexException(String message) {
		super(message);
	}

	public CartOutOfIndexException(Throwable cause) {
		super(cause);
	}

}
