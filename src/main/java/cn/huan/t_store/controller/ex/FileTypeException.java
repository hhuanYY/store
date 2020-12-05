package cn.huan.t_store.controller.ex;

/**
 * 上传的文件类型超出了限制的异常
 */
public class FileTypeException extends FileUploadException {

	private static final long serialVersionUID = 3652563516851916279L;

	public FileTypeException() {
		super();
	}

	public FileTypeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public FileTypeException(String message, Throwable cause) {
		super(message, cause);
	}

	public FileTypeException(String message) {
		super(message);
	}

	public FileTypeException(Throwable cause) {
		super(cause);
	}

}
