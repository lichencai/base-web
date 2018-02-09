package com.zriot.ebike.response;



import java.io.Serializable;

import lombok.Data;

@Data
public class Response<T> implements Serializable {
	
	public static final String FAIL = "0";
	
	public static final String SUCC = "1";
	
	public static final String AUTH = "2";

	private static final long serialVersionUID = 1L;
	private String status;      
    private String msg;         
    private T data;

    private Response(T data, String status, String msg){
        this.data = data;
        this.status = status;
        this.msg = msg;
    }
    
    private Response(String status, String msg){
        this.status = status;
        this.msg = msg;
    }

    public static <T> Response<T> successResponse(T data) {
        return new Response<T>(data, SUCC, "success");
    }
    
    public static <T> Response<T> successResponse() {
        return new Response<T>(SUCC, "success");
    }
    
    public static <T> Response<T> failResponse(T data) {
        return new Response<T>(data, FAIL, "fail");
    }
    
    public static <T> Response<T> failResponse(String msg) {
        return new Response<T>( FAIL, msg);
    }
    
    public static <T> Response<T> authResponse(T data) {
        return new Response<T>(data, AUTH, "fail");
    }

    
}
