package org.bookStore.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.bookStore.pojo.Book;
import org.bookStore.pojo.CartItem;
import org.bookStore.pojo.OrderBean;
import org.bookStore.pojo.User;
import org.bookStore.service.CartItemService;
import org.bookStore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

    @RequestMapping("/checkout")
    public String checkout(HttpSession session){
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

        orderService.addOrderBean(orderBean);
        session.setAttribute("orderNo", orderBean.getOrderNo());

        return "redirect:/toCheckoutPage" ;
    }

    @RequestMapping("/toCheckoutPage")
    public String toCheckoutPage(){
        return "cart/checkout";
    }

    @RequestMapping("/getOrderList")
    public String getOrderList(HttpSession session){
        User user = (User)session.getAttribute("loginUser");

        List<OrderBean> orderList = orderService.getOrderList(user);
        session.setAttribute("orderList",orderList);

        return "order/order" ;
    }

    @RequestMapping("/getAllOrderList")
    public String getAllOrderList(@RequestParam(defaultValue = "1") Integer pageNum, HttpSession session, Model model){
        PageHelper.startPage(pageNum, 10);

        List<OrderBean> orderList = orderService.getOrderList(null);
        //use pageInfo to package the result
        //packaging detailed paging info and search result, 5 pages show each time
        PageInfo orderPageInfo = new PageInfo(orderList, 5);
        model.addAttribute("pageInfo", orderPageInfo);

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

}
