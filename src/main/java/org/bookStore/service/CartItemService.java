package org.bookStore.service;

import org.bookStore.pojo.CartItem;
import org.bookStore.pojo.User;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface CartItemService {
    CartItem getCartItem(Integer id);
    void addCartItem(CartItem cartItem);
    void updateCartItem(CartItem cartItem);
    void addOrUpdateCartItem(CartItem cartItem, User user);

    List<CartItem> getCartItemList(User user);
    Double getTotalCartItemPrice(List<CartItem> cartItemList);

    void deleteCartItem(CartItem cartItem);
}
