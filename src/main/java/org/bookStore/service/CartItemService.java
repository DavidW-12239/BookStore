package org.bookStore.service;

import org.bookStore.pojo.Cart;
import org.bookStore.pojo.CartItem;
import org.bookStore.pojo.User;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface CartItemService {
    CartItem getCartItem(Integer id);
    void addCartItem(CartItem cartItem);
    void updateCartItem(CartItem cartItem);
    void addOrUpdateCartItem(CartItem cartItem , Cart cart);

    //获取指定用户的所有购物车项列表（需要注意的是，这个方法内部查询的时候，会将book的详细信息设置进去）
    List<CartItem> getCartItemList(User user);

    //加载特定用户的购物车信息
    Cart getCart(User user);

    void deleteCartItem(CartItem cartItem);
}
