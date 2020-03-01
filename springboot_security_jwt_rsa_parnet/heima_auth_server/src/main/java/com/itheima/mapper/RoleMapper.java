package com.itheima.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.itheima.domain.SysRole;

public interface RoleMapper {
	
	@Select("SELECT r.id,r.role_name roleName,r.role_desc roleDesc FROM sys_role r, sys_user_role ur WHERE r.id = ur.rid and ur.uid=#{uid}")
	public List<SysRole> findByUid(Integer uid);

}
