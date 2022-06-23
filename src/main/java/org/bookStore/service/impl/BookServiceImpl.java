package org.bookStore.service.impl;


import org.bookStore.mapper.BookMapper;
import org.bookStore.pojo.Book;
import org.bookStore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookMapper bookMapper;

    @Override
    public List<Book> getBookListByStatus(Integer bookStatus) {
        return bookMapper.getBookListByStatus(bookStatus);
    }

    @Override
    public List<Book> getBookList() {
        return bookMapper.getBookList();
    }

    @Override
    public List<Book> getBookListByName(String bookName) {
        return bookMapper.getBookByName(bookName);
    }

    @Override
    public List<Book> getBookListByPrice(Double price1, Double price2) {
        return bookMapper.getBookListByPrice(price1, price2);
    }

    @Override
    public List<Book> getBookListByNameAndPrice(Double price1, Double price2, String bookName) {
        List<Book> bookList = bookMapper.getBookListByPriceAndName(price1, price2, bookName);
        return bookList;
    }

    @Override
    public Book getBook(Integer id) {
        return bookMapper.getBook(id);
    }

    @Override
    public void addBook(Book book) {
        bookMapper.addBook(book);
    }

    @Override
    public void updateBook(Book book) {
        bookMapper.update(book);
    }

    @Override
    public void deleteBook(Integer id) {
        bookMapper.deleteBook(id);
    }


}
