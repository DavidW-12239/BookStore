package org.bookStore.mapper;
import org.apache.ibatis.annotations.Mapper;
import org.bookStore.pojo.Book;

import java.util.List;

@Mapper
public interface BookMapper {
    List<Book> getBookList();
    Book getBook(Integer id);
    void addBook(Book book);
}
