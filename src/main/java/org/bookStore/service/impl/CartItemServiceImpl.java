package org.bookStore.service.impl;


import org.bookStore.mapper.CartItemMapper;
import org.bookStore.pojo.Book;
import org.bookStore.pojo.CartItem;
import org.bookStore.pojo.User;
import org.bookStore.service.BookService;
import org.bookStore.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartItemServiceImpl implements CartItemService {

    @Autowired
    private CartItemMapper cartItemMapper;
    @Autowired
    private BookService bookService ;

    @Override
    public CartItem getCartItem(Integer id) {
        CartItem cartItem = cartItemMapper.getCartItem(id);
        return cartItem;
    }

    @Override
    public void addCartItem(CartItem cartItem) {
        cartItemMapper.addCartItem(cartItem);
    }

    @Override
    public void updateCartItem(CartItem cartItem) {
        cartItemMapper.updateCartItem(cartItem);
    }

    @Override
    public void addOrUpdateCartItem(CartItem cartItem, User user) {
        //exists ? update : add
        List<CartItem> cartItems = cartItemMapper.getCartItemList(user);
        boolean itemExists = false;
        for (CartItem cartItem1 : cartItems){
            if (cartItem.getBook().getId()==cartItem1.getBook().getId()){
                cartItem1.setBuyCount(cartItem1.getBuyCount()+1);
                cartItemMapper.updateCartItem(cartItem1);
                itemExists = true;
            }
        }
        if (itemExists==false){
            cartItemMapper.addCartItem(cartItem);
        }
    }

    @Override
    public List<CartItem> getCartItemList(User user) {
        List<CartItem> cartItemList = cartItemMapper.getCartItemList(user);
        for(CartItem cartItem : cartItemList){
            Book book = bookService.getBook(cartItem.getBook().getId());
            cartItem.setBook(book);
        }

        return cartItemList;
    }

    @Override
    public Double getTotalCartItemPrice(List<CartItem> cartItemList) {
        Double price = 0.0;
        for(CartItem cartItem : cartItemList){
            price += cartItem.getBook().getCurrPrice() * cartItem.getBuyCount();
        }
        return price;
    }

    @Override
    public void deleteCartItem(CartItem cartItem) {
        cartItemMapper.delCartItem(cartItem);
    }
}
