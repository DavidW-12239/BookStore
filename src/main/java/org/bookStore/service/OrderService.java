package org.bookStore.service;


import org.bookStore.pojo.OrderBean;
import org.bookStore.pojo.User;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public interface OrderService {
    void addOrderBean(OrderBean orderBean, HttpSession session) throws Exception;
    List<OrderBean> getOrderList(User user);
    void updateOrderStatus(OrderBean orderBean, Integer orderStatus);
    OrderBean getOrderBeanById(Integer id);
}
