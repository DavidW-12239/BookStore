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
        //1) 订单表添加一条记录
        //2) 订单详情表添加7条记录
        //3) 购物车项表中需要删除对应的7条记录
        //第1步：
        orderMapper.addOrderBean(orderBean);   //执行完这一步，orderBean中的id是有值的
        //第2步：
        //orderBean中的orderItemList是空的，此处我们应该根据用户的购物车中的购物车项去转换成一个一个的订单项
        User loginUser = orderBean.getOrderUser();
        Map<Integer, CartItem> cartItemMap = loginUser.getCart().getCartItemMap();
        for(CartItem cartItem : cartItemMap.values()){
            OrderItem orderItem = new OrderItem();
            orderItem.setBook(cartItem.getBook());
            orderItem.setBuyCount(cartItem.getBuyCount());
            orderItem.setOrderBean(orderBean);
            orderItemMapper.addOrderItem(orderItem);
        }

        //第3步：

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
