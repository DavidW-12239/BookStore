package org.bookStore.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.bookStore.pojo.OrderBean;
import org.bookStore.pojo.User;

import java.util.List;

@Mapper
public interface OrderMapper {

    void addOrderBean(OrderBean orderBean);

    List<OrderBean> getOrderList(User user);

    List<OrderBean> getAllOrderList();

    Integer getOrderTotalBookCount(OrderBean orderBean);

    OrderBean getOrderBean(Integer id);

    OrderBean getOrderBeanByNo(String orderNo);

    void updateOrderStatus(OrderBean orderBean, Integer orderStatus);
}
