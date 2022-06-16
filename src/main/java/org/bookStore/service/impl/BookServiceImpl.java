package org.bookStore.service.impl;


import org.bookStore.mapper.BookMapper;
import org.bookStore.pojo.Book;
import org.bookStore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookMapper bookMapper;

    @Override
    public List<Book> getBookList() {
        return bookMapper.getBookList();
    }

    @Override
    public Book getBook(Integer id) {
        return bookMapper.getBook(id);
    }

    @Override
    public void addBook(Book book) {
        bookMapper.addBook(book);
    }


}
