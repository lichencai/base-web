package com.zriot.ebike.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 数据Entity类
 */
public abstract class DataEntity extends BaseEntity {

	private static final long serialVersionUID = -4055079560794505479L;
	/**
     * 删除标记0：正常
     */
    public static final Integer DEL_FLAG_NORMAL = 0;
    /**
     * 删除标记1：删除
     */
    public static final Integer DEL_FLAG_DELETE = 1;
    /**
     * 创建日期
     */
    private Date createDate;
    /**
     * 更新日期
     */
    private Date updateDate;
    /**
     * 删除标记(0:正常;1:删除;)
     */
    private Integer delFlag;

    public DataEntity() {
        super();
        this.delFlag = DEL_FLAG_NORMAL;
    }
    public DataEntity(Integer id) {
        super(id);
    }
    /**
     * 插入之前执行方法，需要手动调用
     */
    @Override
    public void preInsert() {
        // 不限制ID为UUID，调用setIsNewRecord()使用自定义ID
        if (this.getIsNewRecord()) {
        	
        }
        this.updateDate = new Date();
        this.createDate = this.updateDate;
    }
    /**
     * 更新之前执行方法，需要手动调用
     */
    @Override
    public void preUpdate() {
        this.updateDate = new Date();
    }

    public Date getCreateDate() {
        return createDate == null ? null : (Date) createDate.clone();
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate == null ? null : (Date) createDate.clone();
    }

    public Date getUpdateDate() {
        return updateDate == null ? null : (Date) updateDate.clone();
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate == null ? null : (Date) updateDate.clone();
    }

    @JsonIgnore
    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

}
