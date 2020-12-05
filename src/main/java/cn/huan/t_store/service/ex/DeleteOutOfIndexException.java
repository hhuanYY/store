package cn.huan.t_store.service.ex;

/**
 * 插入数据异常
 */
public class DeleteOutOfIndexException extends ServiceException {


	/**
	 * 
	 */
	private static final long serialVersionUID = 6390575436466789645L;

	public DeleteOutOfIndexException() {
		super();
	}

	public DeleteOutOfIndexException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public DeleteOutOfIndexException(String message, Throwable cause) {
		super(message, cause);
	}

	public DeleteOutOfIndexException(String message) {
		super(message);
	}

	public DeleteOutOfIndexException(Throwable cause) {
		super(cause);
	}

}
