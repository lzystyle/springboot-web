package com.example.demo.service.user.impl;

import com.example.demo.entity.UserInfo;
import com.example.demo.mapper.user.UserInfoMapper;
import com.example.demo.service.user.UserInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserInfoServiceImpl implements UserInfoService{
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    UserInfoMapper userInfoMapper;
    @Override
    public List<UserInfo> queryUsers() {
        return userInfoMapper.queryUsers();
    }
}
