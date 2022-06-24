package org.bookStore.service.impl;

import org.bookStore.mapper.*;
import org.bookStore.pojo.*;
import org.bookStore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
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
    @Autowired
    private BookMapper bookMapper;

    //@Transactional
    @Override
    public void addOrderBean(OrderBean orderBean, HttpSession session) throws Exception {
        User loginUser = orderBean.getOrderUser();
        List<CartItem> cartItemList = loginUser.getCartItemList();
        for(CartItem cartItem : cartItemList){
            Book book = cartItem.getBook();
            Integer bookCount = cartItem.getBuyCount();
            book.setSaleCount(book.getSaleCount() + bookCount);
            book.setBookCount(book.getBookCount() - bookCount);
            if (book.getBookCount()<0){
                session.setAttribute("insufficientMsg", "Insufficient stock for " + book.getBookName());
                throw new Exception("Insufficient stock");
            }
            orderMapper.addOrderBean(orderBean);
            OrderItem orderItem = new OrderItem();
            orderItem.setBook(book);
            orderItem.setBuyCount(bookCount);

            orderItem.setOrderBean(orderMapper.getOrderBeanByNo(orderBean.getOrderNo()));
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
