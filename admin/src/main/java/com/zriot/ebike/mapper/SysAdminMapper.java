package com.zriot.ebike.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.zriot.ebike.dao.CrudDao;
import com.zriot.ebike.entity.SysAdmin;

@Mapper
public interface SysAdminMapper extends CrudDao<SysAdmin> {
	SysAdmin findByLoginName(String loginName);
}
