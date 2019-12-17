package com.test.aliyun_web.mapper;

import com.test.aliyun_web.entity.User;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Date;

public interface UserMapper {
    @Select("select * from user where username =#{username}")
    public User selectUserByUsername(String username);

    @Update("update user set lastLoginDate=#{date} where username=#{username}")
    public void updateUserLoginDateByUsername(String username,Date date);

    @Update("update user set ip =#{ip} where username=#{username}")
    public void updateUserLoginIPByUsername(String username,String ip);
}
