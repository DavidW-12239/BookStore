package org.bookStore.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.bookStore.pojo.OrderBean;
import org.bookStore.pojo.User;

import java.util.List;

@Mapper
public interface OrderMapper {
    //添加订单
    void addOrderBean(OrderBean orderBean);
    //获取指定用户的订单列表
    List<OrderBean> getOrderList(User user);

    Integer getOrderTotalBookCount(OrderBean orderBean);

    OrderBean getOrderBean(Integer id);
}
