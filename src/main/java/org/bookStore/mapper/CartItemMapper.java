package org.bookStore.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.bookStore.pojo.CartItem;
import org.bookStore.pojo.User;

import java.util.List;

@Mapper
public interface CartItemMapper {
    //新增购物车项
    void addCartItem(CartItem cartItem);
    //修改特定的购物车项
    void updateCartItem(CartItem cartItem);
    //获取特定用户的所有购物车项
    List<CartItem> getCartItemList(User user);
    //删除特定的购物车项
    void delCartItem(CartItem cartItem);

    CartItem getCartItem(Integer id);
}
