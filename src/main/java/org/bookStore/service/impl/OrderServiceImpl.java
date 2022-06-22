package org.bookStore.service.impl;

import org.bookStore.mapper.CartItemMapper;
import org.bookStore.mapper.OrderMapper;
import org.bookStore.mapper.OrderItemMapper;
import org.bookStore.mapper.UserMapper;
import org.bookStore.pojo.CartItem;
import org.bookStore.pojo.OrderBean;
import org.bookStore.pojo.OrderItem;
import org.bookStore.pojo.User;
import org.bookStore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    @Autowired
    private UserMapper userMapper;

    @Override
    public void addOrderBean(OrderBean orderBean) {
        orderMapper.addOrderBean(orderBean);

        User loginUser = orderBean.getOrderUser();
        List<CartItem> cartItemList = loginUser.getCartItemList();
        for(CartItem cartItem : cartItemList){
            OrderItem orderItem = new OrderItem();
            orderItem.setBook(cartItem.getBook());
            orderItem.setBuyCount(cartItem.getBuyCount());
            orderItem.setOrderBean(orderBean);
            orderItemMapper.addOrderItem(orderItem);
        }

        for(CartItem cartItem : cartItemList){
            cartItemMapper.delCartItem(cartItem);
        }
    }

    @Override
    public List<OrderBean> getOrderList(User user) {
        List<OrderBean> orderBeanList = new ArrayList<>();
        if (user!=null){
            orderBeanList = orderMapper.getOrderList(user);
        } else{
            orderBeanList = orderMapper.getAllOrderList();
        }

        for (OrderBean orderBean: orderBeanList) {
            Integer totalBookCount = orderMapper.getOrderTotalBookCount(orderBean);
            orderBean.setTotalBookCount(totalBookCount);
            User user1 = userMapper.getUserById(orderBean.getOrderUser().getId());
            orderBean.setOrderUser(user1);
        }

        return orderBeanList;
    }

    @Override
    public void updateOrderStatus(OrderBean orderBean, Integer orderStatus) {
        orderMapper.updateOrderStatus(orderBean, orderStatus);
    }

    @Override
    public OrderBean getOrderBeanById(Integer id) {
        return orderMapper.getOrderBean(id);
    }
}
