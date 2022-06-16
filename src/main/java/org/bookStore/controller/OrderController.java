package org.bookStore.controller;

import org.bookStore.pojo.OrderBean;
import org.bookStore.pojo.User;
import org.bookStore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
public class OrderController {
    @Autowired
    OrderService orderService ;

    @RequestMapping("/checkout")
    public String checkout(HttpSession session){
        OrderBean orderBean = new OrderBean() ;
        Date now = new Date();
        orderBean.setOrderNo(UUID.randomUUID()+"_"+now);
        orderBean.setOrderDate(now);

        User user = (User)session.getAttribute("loginUser");
        orderBean.setOrderUser(user);
        orderBean.setOrderMoney(user.getCart().getTotalMoney());
        orderBean.setOrderStatus(0);

        orderService.addOrderBean(orderBean);
        session.setAttribute("orderId", orderBean.getOrderNo());

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
        user.setOrderList(orderList);

        session.setAttribute("loginUser",user);

        return "order/order" ;
    }
}
