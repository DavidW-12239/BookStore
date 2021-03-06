package org.bookStore;

import org.bookStore.mapper.*;
import org.bookStore.pojo.*;
import org.bookStore.service.BookService;
import org.bookStore.service.CartItemService;
import org.bookStore.service.OrderService;
import org.bookStore.service.UserService;
import org.bookStore.utils.Utils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestClassOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.Date;
import java.util.List;
import java.util.UUID;

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
    void testOrder1(){
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

        /*List<Book> books = bookService.getBookListByNameAndPrice(0.0, 1.0,"ringdafsdg");
        for (Book book : books){
            System.out.println(book.getBookName());
        }*/
        Integer bookId = 3;
        User user = userMapper.getUserById(2);
        CartItem cartItem = new CartItem(null, bookService.getBook(bookId), 1, user);
        System.out.println(cartItem.getBook().getId());

        List<CartItem> cartItems = cartItemMapper.getCartItemList(user);
        boolean itemExists = false;
        for (CartItem cartItem1 : cartItems){
            if (cartItem.getBook().getId()==cartItem1.getBook().getId()){
                cartItem1.setBuyCount(cartItem1.getBuyCount()+1);
                itemExists = true;
                System.out.println(itemExists);
            }
        }
        if (itemExists==false){
            //cartItemMapper.addCartItem(cartItem);
            System.out.println(itemExists);
        }
    }

    @Test
    void testOrder2(){
        OrderBean orderBean = new OrderBean() ;
        Date now = new Date();
        orderBean.setOrderNo(UUID.randomUUID()+"_"+now);
        orderBean.setOrderDate(now);

        User user = userService.getUserById(1);
        List<CartItem> cartItemList = cartItemService.getCartItemList(user);
        Double totalPrice = cartItemService.getTotalCartItemPrice(cartItemList);
        orderBean.setOrderUser(user);
        orderBean.setOrderMoney(totalPrice);
        orderBean.setOrderStatus(0);
        System.out.println(orderBean.getId());
    }

    @Test
    void testGetBookList(){

        List<Book> list = bookService.getAllBookList(0.0, 14.00, null, null, 1);
        System.out.println(list.size());
    }

    @Test
    void testUtils(){
        int[] aa = Utils.randomNumber(0,6,4);
        for (int i:aa){
            System.out.println(i);
        }
    }

    @Test
    @Transactional
    void testTransactional(){
        Book book = bookMapper.getBook(21);
        book.setBookCount(3);
        bookMapper.update(book);
        int i = 5/0;
    }

    @Test
    void testDeleteOrder(){
        OrderBean orderBean = orderMapper.getOrderBean(66);
        orderMapper.delById(orderBean);
    }

    @Test
    void testReview(){
        bookService.updateBookReviews(1, 5);
    }

    @Test
    void testOrderItem(){
        /*List<Integer> list = orderItemMapper.getOrderItem(11);
        System.out.println(list.size());*/
        /*List<OrderItem> list = orderService.getOrderItems(11);
        System.out.println(list.get(0).getBook().getCurrPrice());*/
        OrderItem orderItem = orderItemMapper.getOrderItemById(6);
        orderService.updateOrderItemReviewStatus(orderItem);
        System.out.println(orderService.checkOrderItemReview(orderItem));

    }
}



