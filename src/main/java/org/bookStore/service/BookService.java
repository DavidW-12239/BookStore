package org.bookStore.service;

import org.bookStore.pojo.Book;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookService {
    List<Book> getBookList();
    Book getBook(Integer id);
    void addBook(Book book);
}
