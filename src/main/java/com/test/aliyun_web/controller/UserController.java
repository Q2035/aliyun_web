package com.test.aliyun_web.controller;

import com.test.aliyun_web.config.LoginHandlerInterceptor;
import com.test.aliyun_web.entity.User;
import com.test.aliyun_web.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    /**
     *  setMaxAge() 默认为s
     * 考虑将SessionId存入Cookie 时效设置为30min
     * @param username 用户名
     * @param password 密码
     * @return
     */
    @PostMapping("/user/authentication")
    public String authentication(@RequestParam("username")String username,
                                 @RequestParam("password")String password,
                                 HttpServletRequest request,
                                 HttpServletResponse response) throws IOException {
        User user = userService.selectUserByUsername(username);
        HttpSession session = request.getSession();
        if (user.getPassword().equals(password)){
            Cookie cookie =new Cookie("USER_COOKIE",session.getId());
            cookie.setMaxAge(60*30);
            cookie.setPath("/");
            session.setAttribute(LoginHandlerInterceptor.COOKIE_SESSIONID,session.getId());
            response.addCookie(cookie);
            userService.updateUserLoginDateByUsername(username,new Date());
            userService.updateUserLoginIPByUsername(username,request.getRemoteAddr());
            logger.info(username+" from "+request.getRemoteAddr()+" login");
            return "redirect:/upload";
        }
        return "forward:/upload?msg=账号过期密码错误";

    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

}
