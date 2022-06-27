package org.bookStore.mapper;
import org.apache.ibatis.annotations.Mapper;
import org.bookStore.pojo.Book;
import org.bookStore.pojo.OrderBean;
import org.bookStore.pojo.OrderItem;

import java.util.List;

@Mapper
public interface OrderItemMapper {

    void addOrderItem(OrderItem orderItem);

    void deleteOrderItem(OrderBean orderBean);

    List<OrderItem> getOrderItem(Integer orderId);

    OrderItem getOrderItemById(Integer id);

    Integer checkOrderItemReview(OrderItem orderItem);

    void updateIsReviewedById(Integer id);
}
