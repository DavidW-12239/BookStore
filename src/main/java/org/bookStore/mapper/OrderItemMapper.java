package org.bookStore.mapper;
import org.apache.ibatis.annotations.Mapper;
import org.bookStore.pojo.OrderItem;

@Mapper
public interface OrderItemMapper {
    //添加订单项
    void addOrderItem(OrderItem orderItem);
}
