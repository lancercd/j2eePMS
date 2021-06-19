package com.j2ee.annotation.support;

import com.j2ee.annotation.LoginInfo;
import com.j2ee.dto.LoginType;
import com.j2ee.dto.UserLoginInfo;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * uid参数解析
 */
public class LoginInfoHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {


    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().isAssignableFrom(UserLoginInfo.class) && parameter.hasParameterAnnotation(LoginInfo.class);
    }


    @Override
    public Object resolveArgument(
            MethodParameter methodParameter,
            ModelAndViewContainer modelAndViewContainer,
            NativeWebRequest nativeWebRequest,
            WebDataBinderFactory webDataBinderFactory
    ) throws Exception {

        HttpServletRequest request = nativeWebRequest.getNativeRequest(HttpServletRequest.class);
        if (request == null) {
            return null;
        }

        HttpSession session = request.getSession();

        UserLoginInfo userInfo = new UserLoginInfo();

        Integer uid = (Integer)session.getAttribute("uid");
        String username = (String)session.getAttribute("username");
        LoginType type = (LoginType)session.getAttribute("type");

        if(uid == null || username == null || type == null){
            return null;
        }

        userInfo.setUid(uid);
        userInfo.setType(type);
        userInfo.setUsername(username);

        return userInfo;
    }
}
