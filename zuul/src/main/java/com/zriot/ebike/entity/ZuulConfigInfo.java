package com.zriot.ebike.entity;

public class ZuulConfigInfo extends DataEntity{

	private static final long serialVersionUID = -97913972099697737L;
	private String serviceId;
	private String path;
	private String url;
	private Boolean retryable;
	private Boolean enabled;
	private Boolean stripPrefix;
	private String apiName;
	public String getServiceId() {
		return serviceId;
	}
	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Boolean getRetryable() {
		return retryable;
	}
	public void setRetryable(Boolean retryable) {
		this.retryable = retryable;
	}
	public Boolean getEnabled() {
		return enabled;
	}
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	public Boolean getStripPrefix() {
		return stripPrefix;
	}
	public void setStripPrefix(Boolean stripPrefix) {
		this.stripPrefix = stripPrefix;
	}
	public String getApiName() {
		return apiName;
	}
	public void setApiName(String apiName) {
		this.apiName = apiName;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
