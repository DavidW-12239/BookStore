package org.bookStore.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.bookStore.pojo.Book;
import org.bookStore.pojo.OrderBean;
import org.bookStore.pojo.User;
import org.bookStore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    //@RequestMapping("/toOrderManagerPage")
    public String toOrderManagerPageaaa(@RequestParam(defaultValue = "1") Integer pageNum, HttpSession session, Model model){
        User user = (User)session.getAttribute("loginUser");
        //not a paging search
        //import pageHelper
        //invoke pageHelper, page number and the size of each page
        PageHelper.startPage(pageNum, 10);
        //then the paging search
        List<OrderBean> orderList = orderService.getOrderList(user);
        //use pageInfo to package the result
        //packaging detailed paging info and search result, 5 pages show each time
        PageInfo pageInfo = new PageInfo(orderList, 5);
        model.addAttribute("pageInfo", pageInfo);

        //currPage
        model.addAttribute("pageNum", pageInfo.getPageNum());
        //pageSize
        model.addAttribute("pageSize", pageInfo.getPageSize());
        //first page?
        model.addAttribute("isFirstPage", pageInfo.isIsFirstPage());
        //total page
        model.addAttribute("totalPages", pageInfo.getPages());
        //last page?
        model.addAttribute("isLastPage", pageInfo.isIsLastPage());

        return "manager/book_manager";
    }

}
