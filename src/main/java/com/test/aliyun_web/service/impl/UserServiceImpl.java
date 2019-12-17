package com.test.aliyun_web.service.impl;

import com.test.aliyun_web.entity.User;
import com.test.aliyun_web.mapper.UserMapper;
import com.test.aliyun_web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User selectUserByUsername(String username) {
        return userMapper.selectUserByUsername(username);
    }

    @Override
    public void updateUserLoginDateByUsername(String username, Date date) {
        userMapper.updateUserLoginDateByUsername(username,date);
    }

    @Override
    public void updateUserLoginIPByUsername(String username, String ip) {
        userMapper.updateUserLoginIPByUsername(username,ip);
    }

}
