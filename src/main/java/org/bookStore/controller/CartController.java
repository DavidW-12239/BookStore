package org.bookStore.controller;

import org.bookStore.pojo.CartItem;
import org.bookStore.pojo.User;
import org.bookStore.service.BookService;
import org.bookStore.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class CartController {
    @Autowired
    CartItemService cartItemService ;

    @Autowired
    BookService bookService;

    //load cart info
    @RequestMapping("/toCartPage")
    public String getCart(HttpSession session, Model model){
        User user = (User)session.getAttribute("loginUser");
        List<CartItem> cartItemList = cartItemService.getCartItemList(user);
        user.setCartItemList(cartItemList);
        Double totalPrice = cartItemService.getTotalCartItemPrice(cartItemList);
        session.setAttribute("loginUser",user);
        session.setAttribute("totalPrice", totalPrice);
        //session.setAttribute("insufficientMsg", "");
        return "cart/cart";
    }

    @RequestMapping("/addCart/{id}")
    public String addCartItem(@PathVariable("id") Integer bookId, HttpSession session){
        User user = (User)session.getAttribute("loginUser");
        CartItem cartItem = new CartItem(null, bookService.getBook(bookId), 1, user);

        cartItemService.addOrUpdateCartItem(cartItem, user);
        int cartCount = cartItemService.getCartItemList(user).size();
        session.setAttribute("cartCount", cartCount);

        return "redirect:/index";
    }

    @RequestMapping("/deleteCartItem/{id}")
    public String deleteCartItem(@PathVariable("id") Integer cartItemId){
        CartItem cartItem = cartItemService.getCartItem(cartItemId);

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

}
