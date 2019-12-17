package com.test.aliyun_web.config;

import com.test.aliyun_web.entity.User;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginHandlerInterceptor implements HandlerInterceptor {

    public final static String COOKIE_SESSIONID ="USER_COOKIE";

    /**
     * true 则不拦截
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        HttpSession session = request.getSession();
        String id = (String) session.getAttribute(COOKIE_SESSIONID);
        Cookie[] cookies = request.getCookies();
        if (cookies!=null){
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(COOKIE_SESSIONID)){
                    String value = cookie.getValue();
                    if (value.equals(id)){
                        System.out.println("pass:"+value);
                        return true;
                    }
                }
                System.out.println(cookie.getName());
            }
        }
        request.getRequestDispatcher("/login").forward(request,response);
        return false;
    }
}
