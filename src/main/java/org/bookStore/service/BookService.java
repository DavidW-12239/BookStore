package org.bookStore.service;

import org.bookStore.pojo.Book;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookService {
    List<Book> getBookListByStatus(Integer bookStatus);
    List<Book> getBookList();
    List<Book> getBookListByName(String bookName);
    List<Book> getBookListByPrice(Double price1, Double price2);
    List<Book> getAllBookList(Double price1, Double price2, String bookName, String category, Integer bookStatus);
    Book getBook(Integer id);
    Integer getBookByNameAndAuthor(String bookName, String author);
    void addBook(Book book);
    void updateBook(Book book);
    void updateImg(Integer id, String bookImg);
    void updateBookReviews(Integer id, Integer review);
    void deleteBook(Integer id);

}
