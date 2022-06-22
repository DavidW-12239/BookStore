package org.bookStore.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.bookStore.pojo.CartItem;
import org.bookStore.pojo.User;

import java.util.List;

@Mapper
public interface CartItemMapper {

    void addCartItem(CartItem cartItem);

    void updateCartItem(CartItem cartItem);

    List<CartItem> getCartItemList(User user);

    void delCartItem(CartItem cartItem);

    CartItem getCartItem(Integer id);
}
