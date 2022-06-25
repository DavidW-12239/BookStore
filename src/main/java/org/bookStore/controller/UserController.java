package org.bookStore.controller;

import org.bookStore.pojo.CartItem;
import org.bookStore.service.CartItemService;
import org.bookStore.service.UserService;
import org.bookStore.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

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
            session.setAttribute("loginUser",user);
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
            User user = (User) session.getAttribute("loginUser");
            List<CartItem> cartItemList = cartItemService.getCartItemList(user);
            user.setCartItemList(cartItemList);
            session.setAttribute("loginUser",user);
            int cartItemNum = cartItemList.size();
            session.setAttribute("cartCount", cartItemNum);
            return "redirect:/getMainBookList";
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
        String unameReg = "^[a-zA-Z0-9_-]{4,16}$";
        String pwdReg = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,20}$";
        String emailReg = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";
        if (!userService.checkUserName(uname)){
            model.addAttribute("unameMsg","Same username already exists!");
            return "user/register";
        }
        else if (!uname.matches(unameReg)){
            model.addAttribute("unameMsg","Username should consist of 4-16 alphanumeric characters");
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
        else if (!pwd.matches(pwdReg)){
            model.addAttribute("pwdMsg","Password should contain 6-20 characters, include number or letters");
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
        else if (!email.matches(emailReg)){
            model.addAttribute("emailMsg","Incorrect email address !");
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
