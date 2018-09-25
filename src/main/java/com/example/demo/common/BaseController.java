package com.example.demo.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by zhouqing
 * 基础控制器
 */
public abstract class BaseController {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    protected HttpSession session;
    protected HttpServletRequest request;
    protected HttpServletResponse response;

    /**
     * 设置参数
     * @param request request
     * @param response response
     */
    @ModelAttribute
    public void setRequestResponseSession(HttpServletRequest request, HttpServletResponse response){
        this.request = request;
        this.response = response;
        this.session = request.getSession();
    }

}
