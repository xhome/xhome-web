package org.xhome.web.action;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.xhome.common.constant.Status;
import org.xhome.validator.CommonValidator;
import org.xhome.validator.ValidatorMapping;
import org.xhome.web.response.CommonResult;
import org.xhome.web.response.MsgResult;

/**
 * @project xhome-web
 * @author 	jhat
 * @email 	cpf624@126.com
 * @date 	Jan 18, 20148:22:44 PM
 * @describe 
 */
public class AbstractAction {

	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	protected CommonValidator		commonValidator 	= new CommonValidator();
	protected ValidatorMapping	validatorMapping	= ValidatorMapping.getInstance();
	
	@InitBinder
	public void initBinder(HttpServletRequest request, WebDataBinder binder) {
		String uri = request.getRequestURI();
		if (logger.isDebugEnabled()) {
			logger.debug("init binder for " + uri);
		}
		int index = uri.lastIndexOf(".");
		if (index > 0) {
			uri = uri.substring(0, index);
		}
		commonValidator.setValidators(validatorMapping.getValidatorByUri(uri));
		binder.setValidator(commonValidator);
	}
	
	public CommonResult errorResult(BindingResult result) {
		ObjectError error = result.getAllErrors().get(0);
		if (error != null) {
			return new CommonResult(validatorMapping.convertErrorCode(error.getCode()), error.getDefaultMessage());
		}
		return new CommonResult(Status.ERROR, "未知错误");
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Object handleMethodArgumentNotValidException(
			MethodArgumentNotValidException e) {
		BindingResult result = e.getBindingResult();
		ObjectError error = result.getAllErrors().get(0);
		if (error != null) {
			return new MsgResult(validatorMapping.convertErrorCode(error.getCode()), error.getDefaultMessage());
		}
		return new MsgResult(Status.ERROR, "未知错误");
	}
	
}
