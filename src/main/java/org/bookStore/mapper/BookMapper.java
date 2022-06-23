package org.bookStore.mapper;
import org.apache.ibatis.annotations.Mapper;
import org.bookStore.pojo.Book;

import java.util.List;

@Mapper
public interface BookMapper {
    List<Book> getBookListByStatus(Integer bookStatus);
    List<Book> getBookList();
    Book getBook(Integer id);
    void addBook(Book book);
    void deleteBook(Integer id);
    void update(Book book);
    List<Book> getBookListByPrice(Double price1, Double price2);
    List<Book> getBookByName(String bookName);
    List<Book> getAllBookList(Double price1, Double price2, String bookName, String category);
}
