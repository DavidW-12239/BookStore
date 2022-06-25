package org.bookStore.pojo;

import java.math.BigDecimal;

public class CartItem {
    private Integer id ;
    private Book book ;
    private Integer buyCount ;
    private User userBean ;
    private Double xj ;

    public CartItem(){
    }

    public CartItem(Integer id, Integer buyCount) {
        this.id = id;
        this.buyCount = buyCount;
    }

    public CartItem(Book book, Integer buyCount, User userBean) {
        this.book = book;
        this.buyCount = buyCount;
        this.userBean = userBean;
    }

    public CartItem(Integer id) {
        this.id = id;
    }

    public CartItem(Integer id, Book book, Integer buyCount, User userBean) {
        this.id = id;
        this.book = book;
        this.buyCount = buyCount;
        this.userBean = userBean;
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

    public User getUserBean() {
        return userBean;
    }

    public void setUserBean(User userBean) {
        this.userBean = userBean;
    }

    public Double getXj() {
        BigDecimal bigDecimalPrice = new BigDecimal(""+getBook().getCurrPrice());
        BigDecimal bigDecimalBuyCount = new BigDecimal(""+buyCount);
        BigDecimal bigDecimalXJ = bigDecimalPrice.multiply(bigDecimalBuyCount);
        xj = bigDecimalXJ.doubleValue() ;
        return xj;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "id=" + id +
                ", book=" + book +
                ", buyCount=" + buyCount +
                ", userBean=" + userBean +
                '}';
    }
}
