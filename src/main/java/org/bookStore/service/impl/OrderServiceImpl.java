package org.bookStore.service.impl;

import org.bookStore.mapper.CartItemMapper;
import org.bookStore.mapper.OrderMapper;
import org.bookStore.mapper.OrderItemMapper;
import org.bookStore.pojo.CartItem;
import org.bookStore.pojo.OrderBean;
import org.bookStore.pojo.OrderItem;
import org.bookStore.pojo.User;
import org.bookStore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderItemMapper orderItemMapper;
    @Autowired
    private CartItemMapper cartItemMapper;

    @Override
    public void addOrderBean(OrderBean orderBean) {
        orderMapper.addOrderBean(orderBean);

        User loginUser = orderBean.getOrderUser();
        Map<Integer, CartItem> cartItemMap = loginUser.getCart().getCartItemMap();
        for(CartItem cartItem : cartItemMap.values()){
            OrderItem orderItem = new OrderItem();
            orderItem.setBook(cartItem.getBook());
            orderItem.setBuyCount(cartItem.getBuyCount());
            orderItem.setOrderBean(orderBean);
            orderItemMapper.addOrderItem(orderItem);
        }

        for(CartItem cartItem : cartItemMap.values()){
            cartItemMapper.delCartItem(cartItem);
        }
    }

    @Override
    public List<OrderBean> getOrderList(User user) {
        List<OrderBean> orderBeanList = orderMapper.getOrderList(user);

        for (OrderBean orderBean: orderBeanList) {
            Integer totalBookCount = orderMapper.getOrderTotalBookCount(orderBean);
            orderBean.setTotalBookCount(totalBookCount);
        }

        return orderBeanList ;
    }
}
