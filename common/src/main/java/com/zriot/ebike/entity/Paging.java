package com.zriot.ebike.entity;

import java.io.Serializable;

/**
 * 分页对象
 */
public class Paging implements Serializable {

	private static final long serialVersionUID = 3476212422924600024L;
	/**
     * 页码
     */
    private int pageNum;
    /**
     * 每页条数
     */
    private int pageSize;
    /**
     * 排序字段
     */
    private String orderBy;

    public Paging() {
        this.pageNum = 1;
        this.pageSize = 20;
    }

    public Paging(int pageNum, int pageSize) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }

    public Paging(int pageNum, int pageSize, String orderBy) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.orderBy = orderBy;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }
}
