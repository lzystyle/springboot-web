package com.example.demo.controller;

import com.example.demo.common.BaseResult;
import com.example.demo.entity.UserInfo;
import com.example.demo.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserInfoController {
    @Autowired
    UserInfoService userInfoService;
    @RequestMapping(value = "/list")
    @ResponseBody
    public BaseResult list(){
        BaseResult result = new BaseResult();
        List<UserInfo> userInfos = userInfoService.queryUsers();
        result.setSuccess(true);
        result.setData(userInfos);
        return result;
    }
}
