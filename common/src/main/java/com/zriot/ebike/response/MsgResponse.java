package com.zriot.ebike.response;

import java.util.HashMap;
import java.util.Map;

import com.zriot.ebike.constant.Message;
import com.zriot.ebike.constant.ReturnCode;

public class MsgResponse{
	public static Map<String, Object> responseSuccess(){
		Map<String, Object> map = new HashMap<>();
		map.put(Message.RETURN_FIELD_CODE, ReturnCode.SUCCESS);
		map.put(Message.RETURN_FIELD_DATA, "success");
		return map;
	}
	
	public static Map<String, Object> responseSuccess(Object data){
		Map<String, Object> map = new HashMap<>();
		map.put(Message.RETURN_FIELD_CODE, ReturnCode.SUCCESS);
		map.put(Message.RETURN_FIELD_DATA, data);
		return map;
	}
}
