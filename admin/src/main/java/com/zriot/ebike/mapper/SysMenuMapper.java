package com.zriot.ebike.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.zriot.ebike.dao.CrudDao;
import com.zriot.ebike.entity.SysMenu;

/**
 * 菜单DAO接口
 */
@Mapper
public interface SysMenuMapper extends CrudDao<SysMenu> {
    /**
     * 根据用户查询菜单
     */
    List<SysMenu> findListBySalerId(String salerId);

    /**
     * 根据角色查询菜单
     */
    List<SysMenu> findListByRoleId(String roleId);
    /**
     * 根据父ID查询菜单
     */
    List<SysMenu> findByParentIdsLike(SysMenu menu);

    /**
     * 更新父ID
     */
    int updateParentIds(SysMenu menu);
}
