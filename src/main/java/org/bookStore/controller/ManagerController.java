package org.bookStore.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.bookStore.pojo.Book;
import org.bookStore.pojo.User;
import org.bookStore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ManagerController {

    @RequestMapping("/toAdminPage")
    public String toAdminPage(HttpSession session){
        if (session.getAttribute("loginUser")==null){
            return "user/login";
        }
        User user = (User) session.getAttribute("loginUser");
        if (user.getRole()==1){
            return "manager/manager";
        }
        else{
            return "index";
        }
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
