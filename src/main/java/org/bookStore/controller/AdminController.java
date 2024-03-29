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
public class AdminController {

    @RequestMapping("/toAdminPage")
    public String toAdminPage(HttpSession session, Model model){
        if (session.getAttribute("loginUser")==null){
            return "user/login";
        }
        User user = (User) session.getAttribute("loginUser");
        if (user.getRole()==1){
            return "admin/admin";
        }
        else{
            model.addAttribute("unameMsg","Sorry, only admin allowed.");
            return "index";
        }
    }

    @RequestMapping("/toAddBookPage")
    public String toAddBookPage(){
        return "admin/book_add";
    }

    @RequestMapping("/toOrderManagerPage")
    public String toOrderManagerPage(){
        return "redirect:/getAllOrderList";
    }

    @RequestMapping("/toBookEditPage")
    public String toBookEditPage(){
        return "admin/book_edit";
    }
}
