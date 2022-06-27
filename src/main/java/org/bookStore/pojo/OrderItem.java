package org.bookStore.pojo;

public class OrderItem {
    private Integer id ;
    private Book book ;                 //M:1
    private Integer buyCount ;
    private OrderBean orderBean ;       //M:1
    private Integer isReviewed;

    public OrderItem(){}

    public OrderItem(Integer id) {
        this.id = id;
    }

    public OrderItem(Integer id, Book book, Integer buyCount, OrderBean orderBean, Integer isReviewed) {
        this.id = id;
        this.book = book;
        this.buyCount = buyCount;
        this.orderBean = orderBean;
        this.isReviewed = isReviewed;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Integer getBuyCount() {
        return buyCount;
    }

    public void setBuyCount(Integer buyCount) {
        this.buyCount = buyCount;
    }

    public OrderBean getOrderBean() {
        return orderBean;
    }

    public void setOrderBean(OrderBean orderBean) {
        this.orderBean = orderBean;
    }

    public Integer getIsReviewed() {
        return isReviewed;
    }

    public void setIsReviewed(Integer isReviewed) {
        this.isReviewed = isReviewed;
    }
}
