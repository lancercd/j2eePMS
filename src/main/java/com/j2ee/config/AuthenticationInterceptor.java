package com.j2ee.config;


import com.j2ee.annotation.*;
import com.j2ee.dto.LoginType;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;


@Configuration
public class AuthenticationInterceptor implements HandlerInterceptor {


    private static final Set<Object> LOGIN_ANNOTATIONS;

    static {
        LOGIN_ANNOTATIONS = new HashSet<>(5);
        LOGIN_ANNOTATIONS.add(StudentLogin.class);
        LOGIN_ANNOTATIONS.add(TeacherLogin.class);
        LOGIN_ANNOTATIONS.add(TeachingSecretaryLogin.class);
        LOGIN_ANNOTATIONS.add(AdminLogin.class);
        LOGIN_ANNOTATIONS.add(Login.class);
    }


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {

        if(!(handler instanceof HandlerMethod)){
            return true;
        }

        HandlerMethod handlerMethod=(HandlerMethod)handler;
        Method method=handlerMethod.getMethod();
        if(method.getName().equals("errorHtml")){
            return true;
        }

        Annotation[] declaredAnnotations = method.getDeclaredAnnotations();

        Object annotation = detectHasLoginAnnotations(declaredAnnotations);
        if (annotation != null) {
            if(!handle(annotation, request.getSession())){
                forwardToLogin(request, response);
            }
        }

        return true;
    }


    /**
     * 判断登录的类型是否与方法上的注解一致
     * @param annotation  方法上的登录类型注解
     * @param session     session
     * @return  boolean
     */
    private boolean handle(Object annotation, HttpSession session) {

        Integer uid = (Integer) session.getAttribute("uid");
        String username = (String) session.getAttribute("username");
        LoginType type = (LoginType) session.getAttribute("type");

        System.out.println("the session");
        System.out.println(uid);
        System.out.println(username);
        System.out.println(type);
        System.out.println("---------------------");
        if(uid == null || username == null || type == null){
            return false;
        }


        if (annotation instanceof StudentLogin) {
            if(type != LoginType.STUDENT) {
                return false;
            }

        }else if (annotation instanceof TeacherLogin) {
            if(type != LoginType.TEACHER) {
                return false;
            }

        }else if (annotation instanceof AdminLogin) {
            if(type != LoginType.ADMIN) {
                return false;
            }

        }else if (annotation instanceof TeachingSecretaryLogin) {
            if(type != LoginType.SECRETARY) {
                return false;
            }

        }else if (annotation instanceof Login) {
            if(type != LoginType.STUDENT) {
                return false;
            }

            LoginType[] types = ((Login)annotation).type();

            for (LoginType value : types) {
                if(value == type){
                    return true;
                }
            }

        }
        return true;
    }



    /**
     * 检测注解中是否包含登录注解
     * @param target 目标注解数组
     * @return annotation || null
     */
    private Object detectHasLoginAnnotations(Annotation[] target){

        for (Annotation annotation : target) {
            if (LOGIN_ANNOTATIONS.contains(annotation.annotationType())) {
                return annotation;
            }
        }

        return null;
    }



    /**
     * 跳转登录页面
     * @param request           req
     * @param response          resp
     * @throws ServletException ServletException
     * @throws IOException      IOException
     */
    private void forwardToLogin(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {

        request.getRequestDispatcher("/login").forward(request, response);
    }
}
