package com.itheima.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.itheima.domain.SysUser;

public interface UserMapper{
	
	
	@Select("SELECT * FROM sys_user WHERE username= #{username}")
	@Results({
		@Result(id = true, property="id",column = "id"),
		@Result(property = "roles",column = "id",javaType = List.class,
		many = @Many(select = "com.itheima.mapper.RoleMapper.findByUid"))
	})
	public SysUser findByName(String username);

}
