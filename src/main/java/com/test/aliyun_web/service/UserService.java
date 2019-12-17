package com.test.aliyun_web.service;

import com.test.aliyun_web.entity.User;

import java.util.Date;

public interface UserService {
    public User selectUserByUsername(String username);
    public void updateUserLoginDateByUsername(String username, Date date);
    public void updateUserLoginIPByUsername(String username,String ip);
}
