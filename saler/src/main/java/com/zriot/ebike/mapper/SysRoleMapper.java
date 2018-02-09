package com.zriot.ebike.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.zriot.ebike.dao.CrudDao;
import com.zriot.ebike.entity.SysRole;

/**
 * 角色DAO接口
 */
@Mapper
public interface SysRoleMapper extends CrudDao<SysRole> {

    /**
     * 查询用户角色列表
     */
    List<SysRole> findListBySalerId(String salerId);

    /**
     * 删除角色菜单
     */
    int deleteRoleMenu(SysRole role);

    /**
     * 插入角色菜单
     * @return the int
     */
    int insertRoleMenu(SysRole role);
}
