package com.zriot.ebike.exception;

public class BusinessException extends Exception {

	private static final long serialVersionUID = -3418126170141521131L;

	private String code = null;
	private String error = null;
	private String errorDescription = null;
	private Object[] objs = null;
	
	public BusinessException(String code, String error, String errorDescription, Object[] objs) {
		super();
		this.code = code;
		this.error = error;
		this.errorDescription = errorDescription;
		this.objs = objs;
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getErrorDescription() {
		return errorDescription;
	}

	public void setErrorDescription(String errorDescription) {
		this.errorDescription = errorDescription;
	}

	public Object[] getObjs() {
		return objs;
	}

	public void setObjs(Object[] objs) {
		this.objs = objs;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}