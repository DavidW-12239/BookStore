package org.bookStore.controller;

import org.bookStore.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class ManagerController {

    @RequestMapping("/toAdminPage")
    public String toAdminPage(HttpSession session){
        User user = (User) session.getAttribute("loginUser");
        if (user.getRole()==1){
            return "manager/manager";
        }
        else{
            return "index";
        }
    }

    @RequestMapping("/toBookManagerPage")
    public String toBookManagerPage(){
        return "manager/book_manager";
    }

    @RequestMapping("/toOrderManagerPage")
    public String toOrderManagerPage(){
        return "manager/order_manager";
    }

    @RequestMapping("/toBookEditPage")
    public String toBookEditPage(){
        return "manager/book_edit";
    }
}
