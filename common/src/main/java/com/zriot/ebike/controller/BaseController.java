package com.zriot.ebike.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.zriot.ebike.constant.Message;
import com.zriot.ebike.constant.ReturnCode;
import com.zriot.ebike.editor.StringEditor;
import com.zriot.ebike.exception.BusinessException;
import com.zriot.ebike.exception.BusinessExceptionMsg;
import com.zriot.ebike.util.BeanValidators;
import com.zriot.ebike.util.Collections3;
import com.zriot.ebike.util.SpringContextUtils;

/**
 * 控制器支持类
 */
public abstract class BaseController {

    /**
     * 初始化数据绑定
     * 1. 将所有传递进来的String进行HTML编码，防止XSS攻击
     * 2. 将字段中Date类型转换为String类型
     * @param binder the binder
     */
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        // String类型转换，将所有传递进来的String进行HTML编码，防止XSS攻击
        binder.registerCustomEditor(String.class, new StringEditor());
    }

    /**
     * 处理业务异常
     */
    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, Object> handleBusinessException(BusinessException ex) {
        return makeErrorMessage(ex.getCode(), ex.getError(), getBusinessExceptionMsg().getLocalMsg(ex));
    }

    /**
     * 入参变量异常
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> handleConstraintViolationException(ConstraintViolationException ex) {
        List<String> list = BeanValidators.extractMessage(ex);
        return makeErrorMessage(ReturnCode.INVALID_FIELD,
            "Invalid Field", Collections3.convertToString(list, ";"));
    }

    /**
     * 入参bean异常 
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
    	BindingResult result = ex.getBindingResult();
    	//  处理请求参数bean的异常
    	List<ObjectError> errors = result.getAllErrors();
    	StringBuffer sb = new StringBuffer();
    	for(ObjectError error : errors) {
    		String message = error.getDefaultMessage();
    		String cloumn = null;
    		if(error instanceof FieldError) {
    			cloumn = ((FieldError)error).getField();
    		}
    		message = getBusinessExceptionMsg().getLocalMsg(message, null);
    		sb.append(cloumn).append(":").append(message).append(",");
    	}
        return makeErrorMessage(ReturnCode.INVALID_FIELD, "Invalid Field", sb.toString());
    }
    
    
    /**
	 * 授权失败异常
	 */
	@ExceptionHandler(BadCredentialsException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Map<String, Object> handleBusinessException(BadCredentialsException ex) {
		// 用户名或密码错误
		return makeErrorMessage(ReturnCode.INVALID_GRANT, "Bad credentials", getBusinessExceptionMsg().getLocalMsg("bad.credentials", null));
	}

	/**
	 * 用户不可用异常
	 */
	@ExceptionHandler(DisabledException.class)
	@ResponseStatus(HttpStatus.FORBIDDEN)
	public Map<String, Object> handleBusinessException(DisabledException ex) {
		// 用户被停用
		return makeErrorMessage(ReturnCode.DISABLED_USER, "User Disabled", getBusinessExceptionMsg().getLocalMsg("user.disabled", null));
	}

	/**
	 * 用户没有权限
	 */
	@ExceptionHandler(AccessDeniedException.class)
	@ResponseStatus(HttpStatus.FORBIDDEN)
	public Map<String, Object> handleBusinessException(AccessDeniedException ex) {
		// 用户被停用
		return makeErrorMessage(ReturnCode.UNAUTHORIZED, "User Unauthorized", getBusinessExceptionMsg().getLocalMsg("user.unauthorized", null));
	}
	
	
	/**
     * 创建错误返回的map
     * @param code  the code
     * @param error the error
     * @param desc  the desc
     * @return the map
     */
    protected Map<String, Object> makeErrorMessage(String code, String error, String desc) {
        Map<String, Object> message = new HashMap<>();
        message.put(Message.RETURN_FIELD_CODE, code);
        message.put(Message.RETURN_FIELD_ERROR, error);
        message.put(Message.RETURN_FIELD_ERROR_DESC, desc);
        return message;
    }

    private BusinessExceptionMsg getBusinessExceptionMsg() {
    	return SpringContextUtils.getBean(BusinessExceptionMsg.class);
    }
}
