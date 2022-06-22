package org.bookStore.mapper;
import org.apache.ibatis.annotations.Mapper;
import org.bookStore.pojo.OrderItem;

@Mapper
public interface OrderItemMapper {

    void addOrderItem(OrderItem orderItem);
}
