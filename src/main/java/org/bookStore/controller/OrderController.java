package org.bookStore.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.bookStore.pojo.*;
import org.bookStore.service.BookService;
import org.bookStore.service.CartItemService;
import org.bookStore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import javax.websocket.Session;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
public class OrderController {
    @Autowired
    OrderService orderService ;

    @Autowired
    CartItemService cartItemService;

    @Autowired
    BookService bookService;

    //@Transactional
    @RequestMapping("/checkout")
    public String checkout(HttpSession session) {
        OrderBean orderBean = new OrderBean() ;
        Date now = new Date();
        orderBean.setOrderNo(UUID.randomUUID()+"_"+now);
        orderBean.setOrderDate(now);

        User user = (User)session.getAttribute("loginUser");
        List<CartItem> cartItemList = cartItemService.getCartItemList(user);
        Double totalPrice = cartItemService.getTotalCartItemPrice(cartItemList);
        orderBean.setOrderUser(user);
        orderBean.setOrderMoney(totalPrice);
        orderBean.setOrderStatus(0);

        try {
            orderService.addOrderBean(orderBean, session);
            session.setAttribute("orderNo", orderBean.getOrderNo());
            return "redirect:/toCheckoutPage";
        } catch (Exception e) {
            return "redirect:/toCartPage2";
        }
    }

    @RequestMapping("/toCheckoutPage")
    public String toCheckoutPage(){
        return "cart/checkout";
    }

    @RequestMapping("/getOrderList")
    public String getOrderList(HttpSession session){
        User user = (User)session.getAttribute("loginUser");

        List<OrderBean> orderList = orderService.getOrderList(user);
        session.setAttribute("orderList", orderList);

        return "order/order" ;
    }

    @RequestMapping("/getAllOrderList")
    public String getAllOrderList(@RequestParam(defaultValue = "1") Integer pageNum, Model model){
        PageHelper.startPage(pageNum, 10);

        List<OrderBean> orderList = orderService.getOrderList(null);
        //use pageInfo to package the result
        //packaging detailed paging info and search result, 5 pages show each time
        PageInfo orderPageInfo = new PageInfo(orderList, 5);
        model.addAttribute("orderPageInfo", orderPageInfo);

        //currPage
        model.addAttribute("pageNum", orderPageInfo.getPageNum());
        //pageSize
        model.addAttribute("pageSize", orderPageInfo.getPageSize());
        //first page?
        model.addAttribute("isFirstPage", orderPageInfo.isIsFirstPage());
        //total page
        model.addAttribute("totalPages", orderPageInfo.getPages());
        //last page?
        model.addAttribute("isLastPage", orderPageInfo.isIsLastPage());

        return "manager/order_manager";
    }

    @RequestMapping("/changeOrderStatus/{id}")
    public String changeOrderStatus(@RequestParam("orderStatus") String orderStatus, @PathVariable Integer id){
        Integer a = Integer.parseInt(orderStatus);
        OrderBean orderBean = orderService.getOrderBeanById(id);
        orderService.updateOrderStatus(orderBean, a);
        return "redirect:/getAllOrderList";
    }

    @RequestMapping("/confirmReceipt/{id}")
    public String confirmReceipt(@PathVariable("id") Integer id, HttpSession session){
        OrderBean orderBean = orderService.getOrderBeanById(id);
        orderService.updateOrderStatus(orderBean, 3);
        session.setAttribute("reviewItems", orderService.getOrderItems(id));
        return "redirect:/toConfirmReceiptPage";
    }

    @RequestMapping("/toConfirmReceiptPage")
    public String toConfirmReceiptPage(){
        return "order/confirm_receipt";
    }

    @RequestMapping("/cancelOrder/{id}")
    public String cancelOrder(@PathVariable("id") Integer id){
        OrderBean orderBean = orderService.getOrderBeanById(id);
        orderService.updateOrderStatus(orderBean, 4);
        return "redirect:/getOrderList";
    }

    @RequestMapping("/deleteOrder/{id}")
    public String deleteOrder(@PathVariable("id") Integer id){
        OrderBean orderBean = orderService.getOrderBeanById(id);
        orderService.deleteOrderById(orderBean);
        return "redirect:/getOrderList";
    }

    @RequestMapping("/reviewOrder/{id}")
    public String toReviewPageFromOrderPage(@PathVariable("id") Integer id, HttpSession session){
        session.setAttribute("reviewItems", orderService.getOrderItems(id));
        return "redirect:/toReviewPage";
    }

    @RequestMapping("/toReviewPage")
    public String toReviewPage(){
        return "order/order_review";
    }

    @RequestMapping("/submitReview/{id}")
    public String submitReview(@PathVariable("id") Integer id, @RequestParam("review") Integer review, HttpSession session){
        OrderItem currOrderItem = orderService.getOrderItemById(id);
        orderService.updateOrderItemReviewStatus(currOrderItem);
        bookService.updateBookReviews(currOrderItem.getBook().getId(), review);
        OrderBean orderBean = currOrderItem.getOrderBean();
        session.setAttribute("reviewItems", orderService.getOrderItems(orderBean.getId()));

        for (OrderItem orderItem: orderService.getOrderItems(orderBean.getId())){
            if (orderItem.getIsReviewed() == 0){
                return "redirect:/toReviewPage";
            }
        }

        orderService.updateOrderStatus(orderBean, 5);
        return "redirect:/getOrderList";
    }

}
