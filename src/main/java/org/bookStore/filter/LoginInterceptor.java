package org.bookStore.filter;

import org.bookStore.pojo.User;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // get session
        HttpSession session = request.getSession();
        // get user by session
        User user = (User) session.getAttribute("loginUser");
        // redirect
        if (user == null) {
            response.sendRedirect("/");
            return false;
        }
        // already login
        return true;
    }
}
