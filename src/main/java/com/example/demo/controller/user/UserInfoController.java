package com.example.demo.controller.user;

import com.example.demo.common.BaseResult;
import com.example.demo.entity.UserInfo;
import com.example.demo.service.common.RedisService;
import com.example.demo.service.user.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserInfoController {
    @Autowired
    UserInfoService userInfoService;

    @Resource
    RedisService redisService;
    @RequestMapping(value = "/list")
    @ResponseBody
    public BaseResult list(){
        BaseResult result = new BaseResult();
        List<UserInfo> userInfos = userInfoService.queryUsers();
        redisService.addListValue("users",userInfos);
        result.setSuccess(true);
        result.setData(userInfos);
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/getRedisList")
    public BaseResult getRedisUsers(){
        BaseResult result = new BaseResult();
        if(redisService.getListValue("users") != null){
            List<UserInfo> userInfos = (List<UserInfo>) redisService.getListValue("users");
            result.setSuccess(true);
            result.setData(userInfos);
            return result;
        }
        result.setSuccess(false);
        return result;
    }
}
