package com.zriot.ebike.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.BasicErrorController;
import org.springframework.boot.autoconfigure.web.DefaultErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zriot.ebike.constant.Message;
import com.zriot.ebike.exception.BusinessExceptionMsg;

/**
 * ERROR处理控制器
 */
@Controller
@RequestMapping("/error")
public class CustomErrorController extends BasicErrorController {
	
	protected static final Logger LOGGER = LoggerFactory.getLogger(CustomErrorController.class);
	
	@Autowired
	BusinessExceptionMsg businessExceptionMsg;
	
    public CustomErrorController() {
        super(new DefaultErrorAttributes(), new ErrorProperties());
    }

    @RequestMapping
    @ResponseBody
    @Override
    public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {
    	Map<String, Object> body = getErrorAttributes(request,
                isIncludeStackTrace(request, MediaType.ALL));
    	LOGGER.info("error message : {}", body.toString());
    	String errorMsg = Message.RETURN_FIELD_ERROR;
    	//  由于请求头没有带Authorization导致的错误(由类MyAuthenticationEntryPoint触发)
    	if("unauthorized".equals(body.get("message"))) {
    		errorMsg = "user.not.authorized";
    	}
        HttpStatus status = this.getStatus(request);
        Map<String, Object> message = new HashMap<>();
        message.put(Message.RETURN_FIELD_CODE, Message.STATUS_CODE_MAP.get(status.value()));
        message.put(Message.RETURN_FIELD_ERROR, "service error");
        message.put(Message.RETURN_FIELD_ERROR_DESC, businessExceptionMsg.getLocalMsg(errorMsg, null));
        return new ResponseEntity<>(message, status);
    }

}
