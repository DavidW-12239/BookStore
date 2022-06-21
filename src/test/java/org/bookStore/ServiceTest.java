package org.bookStore;

import org.bookStore.mapper.*;
import org.bookStore.pojo.*;
import org.bookStore.service.BookService;
import org.bookStore.service.CartItemService;
import org.bookStore.service.OrderService;
import org.bookStore.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestClassOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.Date;
import java.util.List;

@SpringBootTest
public class ServiceTest {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    DataSource dataSource;

    @Autowired
    UserMapper userMapper;

    @Autowired
    BookMapper bookMapper;

    @Autowired
    CartItemMapper cartItemMapper;

    @Autowired
    OrderItemMapper orderItemMapper;

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    UserService userService;

    @Autowired
    CartItemService cartItemService;

    @Autowired
    OrderService orderService;

    @Autowired
    BookService bookService;

    /*@Test
    void loginServiceTest(){
        User user = userService.login("lina", "ok");
        System.out.println(user.getUname());
    }*/

    @Test
    void testInsertCartItem(){
        Book book = bookMapper.getBook(1);
        User user = userMapper.getUser("lina", "ok");
        CartItem cartItem = new CartItem(null, book, 10, user);
        cartItemService.addCartItem(cartItem);
    }

    @Test
    void testAddUser(){
        User user = new User("david", "123", "q@b", 1);
        userService.register(user);
    }

    @Test
    void testOrder(){
        User user = userService.getUserById(1);

        //List<OrderBean> list = orderService.getOrderList(user);
        List<OrderBean> list = orderService.getOrderList(user);

        OrderBean orderBean = list.get(1);
        System.out.println(orderBean.getOrderStatus());
    }

    @Test
    void testCart(){
        User user = userMapper.getUser("lina", "ok");
        //Cart cart = cartItemService.getCart(user);
        List<CartItem> cartItemList = cartItemMapper.getCartItemList(user);
        for(CartItem cartItem : cartItemList){
            System.out.println(cartItem.toString());
        }
    }

    @Test
    void editBook(){
/*        List<Book> books = bookService.getBookListByStatus(1);
        for (Book book : books){
            System.out.println(book.getBookName());
        }*/
        /*List<Book> books = bookService.getBookListByName("");
        for (Book book : books){
            System.out.println(book.getBookName());
        }*/

        List<Book> books = bookService.getBookListByNameAndPrice(0.0, 100.0,"");
        for (Book book : books){
            System.out.println(book.getBookName());
        }
    }
}



