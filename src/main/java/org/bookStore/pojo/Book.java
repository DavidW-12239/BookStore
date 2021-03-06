package org.bookStore.pojo;

public class Book {
    private Integer id;
    private String bookImg;
    private String bookName;
    private Double currPrice;
    private Double origPrice;
    private String author;
    private Integer saleCount;
    private Integer bookCount;
    private Integer bookStatus;
    private String category;
    private Integer reviewNum;
    private Double review;

    public Book(){}

    public Book(Integer id) {
        this.id = id;
    }

    public Book(Integer id, String bookImg, String bookName, Double currPrice, Double origPrice, String author,
                Integer saleCount, Integer bookCount, Integer bookStatus, String category, Integer reviewNum, Double review) {
        this.id = id;
        this.bookImg = bookImg;
        this.bookName = bookName;
        this.currPrice = currPrice;
        this.origPrice = origPrice;
        this.author = author;
        this.saleCount = saleCount;
        this.bookCount = bookCount;
        this.bookStatus = bookStatus;
        this.category = category;
        this.reviewNum = reviewNum;
        this.review = review;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBookImg() {
        return bookImg;
    }

    public void setBookImg(String bookImg) {
        this.bookImg = bookImg;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getSaleCount() {
        return saleCount;
    }

    public void setSaleCount(Integer saleCount) {
        this.saleCount = saleCount;
    }

    public Integer getBookCount() {
        return bookCount;
    }

    public void setBookCount(Integer bookCount) {
        this.bookCount = bookCount;
    }

    public Integer getBookStatus() {
        return bookStatus;
    }

    public void setBookStatus(Integer bookStatus) {
        this.bookStatus = bookStatus;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getCurrPrice() {
        return currPrice;
    }

    public void setCurrPrice(Double currPrice) {
        this.currPrice = currPrice;
    }

    public Double getOrigPrice() {
        return origPrice;
    }

    public void setOrigPrice(Double origPrice) {
        this.origPrice = origPrice;
    }

    public Integer getReviewNum() {
        return reviewNum;
    }

    public void setReviewNum(Integer reviewNum) {
        this.reviewNum = reviewNum;
    }

    public Double getReview() {
        return review;
    }

    public void setReview(Double review) {
        this.review = review;
    }
}
