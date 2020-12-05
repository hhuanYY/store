package cn.huan.t_store.service.ex;

/**
 * 插入数据异常
 */
public class UserNotMatchingException extends ServiceException {


	/**
	 * 
	 */
	private static final long serialVersionUID = -1430273467153111806L;

	public UserNotMatchingException() {
		super();
	}

	public UserNotMatchingException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public UserNotMatchingException(String message, Throwable cause) {
		super(message, cause);
	}

	public UserNotMatchingException(String message) {
		super(message);
	}

	public UserNotMatchingException(Throwable cause) {
		super(cause);
	}

}
