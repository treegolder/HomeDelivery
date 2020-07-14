package com.delivery.HomeDelivery.HD.interceptor;

import com.delivery.HomeDelivery.HD.component.MyToken;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler,HttpSession session) throws Exception {
        if (session.getAttribute("userid") != null)
            return true;

        return false;
    }
}
