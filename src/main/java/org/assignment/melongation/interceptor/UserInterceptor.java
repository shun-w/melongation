package org.assignment.melongation.interceptor;


import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;

@Component
public class UserInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Cookie[] cookies = request.getCookies();
        String username="";
        for (int i = 0; i < cookies.length; i++) {

            if (cookies[i].getName().equals("username")) {
                username = URLDecoder.decode(cookies[i].getValue(), "utf-8");
            }
        }
        if (!StringUtils.isEmpty(username)) {
            System.out.println("当前用户:"+username);
            request.setAttribute("username", username);
            return true;
        }
        request.setAttribute("msg","您还未登录");
        request.getRequestDispatcher("/user/login").forward(request,response);
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
