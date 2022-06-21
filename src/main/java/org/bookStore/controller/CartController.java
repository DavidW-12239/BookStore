package org.bookStore.controller;

import org.bookStore.pojo.Book;
import org.bookStore.pojo.Cart;
import org.bookStore.pojo.CartItem;
import org.bookStore.pojo.User;
import org.bookStore.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class CartController {
    @Autowired
    CartItemService cartItemService ;

    //加载当前用户的购物车信息
    @RequestMapping("/toCartPage")
    public String getCart(HttpSession session, Model model){
        User user = (User)session.getAttribute("loginUser");
        Cart cart = cartItemService.getCart(user);
        user.setCart(cart);
        session.setAttribute("loginUser",user);
        return "cart/cart";
    }

    @RequestMapping("/addCart/{id}")
    public String addCartItem(@PathVariable("id") Integer bookId, HttpSession session, Model model){
        User user = (User)session.getAttribute("loginUser");
        CartItem cartItem = new CartItem(new Book(bookId),1,user);
        //将指定的图书添加到当前用户的购物车中
        cartItemService.addOrUpdateCartItem(cartItem,user.getCart());
        int cartCount = cartItemService.getCartItemList(user).size();
        session.setAttribute("cartCount", cartCount);

        return "redirect:/index";
    }

    @RequestMapping("/deleteCartItem/{id}")
    public String deleteCartItem(@PathVariable("id") Integer cartItemId, HttpSession session){
        CartItem cartItem = cartItemService.getCartItem(cartItemId);
        //将指定的图书添加到当前用户的购物车中
        cartItemService.deleteCartItem(cartItem);

        return "redirect:/toCartPage";
    }

    @RequestMapping("/reduceCartItemQuantity/{id}")
    public String reduceCartItemQuantity(@PathVariable("id") Integer cartItemId){
        Integer buyCount = cartItemService.getCartItem(cartItemId).getBuyCount() - 1;
        cartItemService.updateCartItem(new CartItem(cartItemId , buyCount));

        return "redirect:/toCartPage";
    }

    @RequestMapping("/increaseCartItemQuantity/{id}")
    public String increaseCartItemQuantity(@PathVariable("id") Integer cartItemId){
        Integer buyCount = cartItemService.getCartItem(cartItemId).getBuyCount() + 1;
        cartItemService.updateCartItem(new CartItem(cartItemId , buyCount));

        return "redirect:/toCartPage";
    }

    /*public String cartInfo(HttpSession session){
        User user =(User)session.getAttribute("loginUser");
        Cart cart = cartItemService.getCart(user);

        cart.getTotalBookCount();
        cart.getTotalCount();
        cart.getTotalMoney();

        Gson gson = new Gson();
        String cartJsonStr = gson.toJson(cart);
        return "json:"+cartJsonStr ;
    }*/
}
