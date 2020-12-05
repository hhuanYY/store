package cn.huan.t_store.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import cn.huan.t_store.controller.ex.FileEmptyException;
import cn.huan.t_store.controller.ex.FileIOException;
import cn.huan.t_store.controller.ex.FileSizeException;
import cn.huan.t_store.controller.ex.FileStateException;
import cn.huan.t_store.controller.ex.FileTypeException;
import cn.huan.t_store.controller.ex.FileUploadException;
import cn.huan.t_store.service.ex.AddressCountException;
import cn.huan.t_store.service.ex.AddressNotFoundException;
import cn.huan.t_store.service.ex.CartNotException;
import cn.huan.t_store.service.ex.CartOutOfIndexException;
import cn.huan.t_store.service.ex.DeleteException;
import cn.huan.t_store.service.ex.DeleteOutOfIndexException;
import cn.huan.t_store.service.ex.InsertException;
import cn.huan.t_store.service.ex.PasswordNotMatchException;
import cn.huan.t_store.service.ex.ProductNotFoundException;
import cn.huan.t_store.service.ex.ServiceException;
import cn.huan.t_store.service.ex.UpdateException;
import cn.huan.t_store.service.ex.UserNotFoundException;
import cn.huan.t_store.service.ex.UserNotMatchingException;
import cn.huan.t_store.service.ex.UsernameDuplicateException;
import cn.huan.t_store.util.JsonResult;



@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler({ServiceException.class, FileUploadException.class})
	public JsonResult<Void> handleException(Throwable e) {
		JsonResult<Void> jsonResult = new JsonResult<>(e);

		if (e instanceof UsernameDuplicateException) {
			jsonResult.setState("4000");
		} else if (e instanceof UserNotFoundException) {
			jsonResult.setState("4001");
		} else if (e instanceof PasswordNotMatchException) {
			jsonResult.setState("4002");
		} else if (e instanceof AddressCountException) {
			jsonResult.setState("4003");
		} else if (e instanceof AddressNotFoundException) {
			jsonResult.setState("4004");
		} else if (e instanceof ProductNotFoundException) {
			jsonResult.setState("4005");
		} else if (e instanceof CartNotException) {
			jsonResult.setState("4006");
		} else if (e instanceof InsertException) {
			jsonResult.setState("5000");
		} else if (e instanceof UpdateException) {
			jsonResult.setState("5001");
		} else if (e instanceof DeleteException) {
			jsonResult.setState("5002");
		} else if (e instanceof FileEmptyException) {
			jsonResult.setState("6000");
		} else if (e instanceof FileSizeException) {
			jsonResult.setState("6001");
		} else if (e instanceof FileTypeException) {
			jsonResult.setState("6002");
		} else if (e instanceof FileStateException) {
			jsonResult.setState("6003");
		} else if (e instanceof FileIOException) {
			jsonResult.setState("6004");
		} else if (e instanceof DeleteOutOfIndexException) {
			jsonResult.setState("6005");
		} else if (e instanceof UserNotMatchingException) {
			jsonResult.setState("6006");
		} else if (e instanceof CartOutOfIndexException) {
			jsonResult.setState("6007");
		}
		return jsonResult;
	}
	
}
