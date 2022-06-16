package org.bookStore.service;


import org.bookStore.pojo.OrderBean;
import org.bookStore.pojo.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {
    void addOrderBean(OrderBean orderBean);
    List<OrderBean> getOrderList(User user);
}
