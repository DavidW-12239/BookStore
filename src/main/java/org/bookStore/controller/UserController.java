package org.bookStore.controller;

import org.apache.catalina.Session;
import org.bookStore.pojo.Cart;
import org.bookStore.service.CartItemService;
import org.bookStore.service.UserService;
import org.bookStore.service.impl.*;
import org.bookStore.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    CartItemService cartItemService;

    @GetMapping(value = "/")
    public String loginPage(){
        return "user/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("uname") String uname, @RequestParam("pwd") String pwd, HttpSession session, Model model){
        User user = userService.login(uname, pwd);
        if(user!=null){
            Cart cart = cartItemService.getCart(user);
            user.setCart(cart);
            session.setAttribute("loginUser",user);
            int cartCount = user.getCart().getTotalCount();
            session.setAttribute("cartCount", cartCount);
            return "user/login_success";
        }
        else{
            if (uname.equals("")){
                model.addAttribute("unameMsg","Please enter the username");
            }
            else if (pwd.equals("")){
                model.addAttribute("pwdMsg","Password cannot be empty");
            }
            else{
                model.addAttribute("unameMsg","Incorrect username or password, please check again");
            }
            return "user/login";
        }
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.setAttribute("loginUser",null);
        return "user/login";
    }

    @GetMapping(value = "/index")
    public String toIndexPage(HttpSession session){
        if (session.getAttribute("loginUser") != null){
            return "redirect:/book";
        }
        return "user/login";
    }

    @RequestMapping("/toRegisterPage")
    public String toRegisterPage(){
        return "user/register";
    }

    @PostMapping("/register")
    public String register(@RequestParam("uname") String uname, @RequestParam("pwd") String pwd,
                           @RequestParam("pwd2") String pwd2, @RequestParam("email") String email,
                           HttpSession session, Model model){
        if (!userService.checkUserName(uname)){
            model.addAttribute("unameMsg","Same username already exists!");
            return "user/register";
        }
        else if (uname.equals("")){
            model.addAttribute("unameMsg","Please enter the username!");
            return "user/register";
        }
        else if (pwd.equals("")&&pwd2.equals("")){
            model.addAttribute("pwdMsg1","Please enter the password!");
            return "user/register";
        }
        else if (!pwd.equals(pwd2)){
            model.addAttribute("pwdMsg2","Different Passwords!");
            return "user/register";
        }
        else if (!userService.checkEmail(email)){
            model.addAttribute("emailMsg","Same email address already exists!");
            return "user/register";
        }
        else if (email.equals("")){
            model.addAttribute("emailMsg","Email address cannot be empty!");
            return "user/register";
        }
        else{
            User loginUser = new User(uname , pwd , email,0);
            userService.register(loginUser);
            session.setAttribute("loginUser", loginUser);
            return "user/register_success";
        }
    }


/*    @RequestMapping("/updateCartCount")
    public String updateCartCount(HttpSession session){
        User user = (User) session.getAttribute("loginUser");
    }*/

}
