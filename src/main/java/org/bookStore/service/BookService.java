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
    List<Book> getAllBookList(Double price1, Double price2, String bookName, String category);
    Book getBook(Integer id);
    void addBook(Book book);
    void updateBook(Book book);
    void deleteBook(Integer id);

}
