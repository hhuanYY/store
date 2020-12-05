package cn.huan.t_store.controller.ex;

/**
 * 上传时，用户没有选择文件，或选择的是0字节的文件时，出现的异常
 */
public class FileEmptyException extends FileUploadException {

	private static final long serialVersionUID = 5216328143694529891L;

	public FileEmptyException() {
		super();
	}

	public FileEmptyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public FileEmptyException(String message, Throwable cause) {
		super(message, cause);
	}

	public FileEmptyException(String message) {
		super(message);
	}

	public FileEmptyException(Throwable cause) {
		super(cause);
	}

}
