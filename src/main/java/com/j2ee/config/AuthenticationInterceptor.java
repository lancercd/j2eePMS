package com.j2ee.config;


import com.j2ee.annotation.Login;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;



@Configuration
public class AuthenticationInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        if(!(handler instanceof HandlerMethod)){
            return true;
        }

        HandlerMethod handlerMethod=(HandlerMethod)handler;
        Method method=handlerMethod.getMethod();

        if (method.isAnnotationPresent(Login.class)) {
            HttpSession session = request.getSession();

            System.out.println(session.getAttribute("uid"));
            System.out.println(session.getAttribute("username"));


        }

        return true;
    }
}
