package org.bookStore.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.bookStore.pojo.Book;
import org.bookStore.service.BookService;
import org.bookStore.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class BookController {
    @Autowired
    BookService bookService;

    @PostMapping("/addBook")
    public String addBook(@RequestParam("bookName") String bookName, @RequestParam("price") Double price,
                          @RequestParam("author") String author, @RequestParam("saleCount") Integer saleCount,
                          @RequestParam("bookCount") Integer bookCount, @RequestParam("bookStatus") Integer bookStatus,
                          @RequestParam("category") String category, Model model){
        String quantityReg = "^[1-9]\\d*$";
        if (bookName.equals("")){
            model.addAttribute("bookNameMsg","Book name cannot be empty!");
            return "manager/book_add";
        }
        else if (price<=0){
            model.addAttribute("priceMsg","Please enter a correct price!");
            return "manager/book_add";
        }
        else if (author.equals("")){
            model.addAttribute("authorMsg","Author cannot be empty!");
            return "manager/book_add";
        }
        else if (!saleCount.toString().matches(quantityReg)){
            model.addAttribute("saleCountMsg","Please enter a correct saleCount!");
            return "manager/book_add";
        }
        else if (!bookCount.toString().matches(quantityReg)){
            model.addAttribute("bookCountMsg","Please enter a correct bookCount!");
            return "manager/book_add";
        }
        else if (category.equals("")){
            model.addAttribute("categoryMsg","Book category cannot be empty!");
            return "manager/book_add";
        }

        bookService.addBook(new Book(null, null, bookName, price, author, saleCount, bookCount, bookStatus, category));
        return "redirect:/toAddingSuccessfulPage";
    }

    @RequestMapping("/toAddingSuccessfulPage")
    public String toAddingSuccessfulPage(){
        return "manager/adding_successful";
    }

    @RequestMapping("/getBookByCategory")
    public String getBookByCategory(@RequestParam("category") String category, HttpSession session){
        session.setAttribute("category", category);
        return "redirect:/getMainBookList";
    }

    @RequestMapping("/getMainBookList")
    public String getMainBookList(@RequestParam(required=false, defaultValue = "0.0", name="price1") Double price1,
                                  @RequestParam(required=false, defaultValue = "1000.0", name="price2") Double price2,
                                  @RequestParam(required=false, defaultValue = "") String bookName,
            @RequestParam(defaultValue = "1") Integer pageNum, Model model, HttpSession session){

        //import pageHelper
        //invoke pageHelper, page number and the size of each page

        //then the paging search
        if (price1==null || price1.isNaN()){
            price1 = 0.0;
        }
        if (price2==null || price2.isNaN()){
            price2 = 1000.0;
        }
        if (bookName==null){
            bookName="";
        }
        String category = "";
        if (session.getAttribute("category")==null){
            category="";
        } else {
            category = (String) session.getAttribute("category");
        }
        session.setAttribute("price1", price1);
        session.setAttribute("price2", price2);
        session.setAttribute("bookName", bookName);

        //use pageInfo to package the result
        //packaging detailed paging info and search result, 5 pages show each time
        PageHelper.startPage(pageNum, 10);
        //then the paging search
        List<Book> bookList = bookService.getAllBookList(price1, price2, bookName, category);
        //use pageInfo to package the result
        //packaging detailed paging info and search result, 5 pages show each time
        PageInfo<Book> pageInfo = new PageInfo<>(bookList, 5);
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

        //get discount book list
        getDiscountBookList(session);

        return "index";
    }

    @RequestMapping("/clearAll")
    public String clearAll(HttpSession session){
        session.setAttribute("price1", 0);
        session.setAttribute("price2", 1000);
        session.setAttribute("bookName", "");
        session.setAttribute("category", "");
        return "redirect:/getMainBookList";
    }

    //get four random discount books
    public void getDiscountBookList(HttpSession session){
        List<Book> discountBookList = bookService.getBookListByStatus(1);
        int[] randomNum = Utils.randomNumber(0,discountBookList.size(),4);
        List<Book> randomList = new ArrayList<>();
        for (int i: randomNum){
            randomList.add(discountBookList.get(i));
        }
        session.setAttribute("discountBookList", randomList);
    }


    @RequestMapping("/toBookManagerPage")
    public String toBookManagerPage(@RequestParam(defaultValue = "1") Integer pageNum, Model model){
        //not a paging search
        //import pageHelper
        //invoke pageHelper, page number and the size of each page
        PageHelper.startPage(pageNum, 10);
        //then the paging search
        List<Book> bookList = bookService.getBookList();
        //use pageInfo to package the result
        //packaging detailed paging info and search result, 5 pages show each time
        PageInfo bookManagerPageInfo = new PageInfo(bookList, 5);
        model.addAttribute("pageInfo", bookManagerPageInfo);

        //currPage
        model.addAttribute("pageNum", bookManagerPageInfo.getPageNum());
        //pageSize
        model.addAttribute("pageSize", bookManagerPageInfo.getPageSize());
        //first page?
        model.addAttribute("isFirstPage", bookManagerPageInfo.isIsFirstPage());
        //total page
        model.addAttribute("totalPages", bookManagerPageInfo.getPages());
        //last page?
        model.addAttribute("isLastPage", bookManagerPageInfo.isIsLastPage());

        return "manager/book_manager";
    }

    @RequestMapping("/deleteBook/{id}")
    public String deleteBook(@PathVariable("id") Integer bookId){
        bookService.deleteBook(bookId);
        return "redirect:/toBookManagerPage";
    }

    @RequestMapping("/getCurrBook/{id}")
    public String getCurrBook(@PathVariable("id") Integer bookId, HttpSession session){
        session.setAttribute("book", bookService.getBook(bookId));
        return "redirect:/toEditBookPage";
    }

    @RequestMapping("/toEditBookPage")
    public String toEditBookPage(){
        return "manager/book_edit";
    }

    @PostMapping("/editBook")
    public String editBook(@RequestParam("bookName") String bookName, @RequestParam("price") Double price,
                          @RequestParam("author") String author, @RequestParam("saleCount") Integer saleCount,
                          @RequestParam("bookCount") Integer bookCount, @RequestParam("bookStatus") Integer bookStatus,
                          @RequestParam("category") String category, HttpSession session, Model model){
        String quantityReg = "^[1-9]\\d*$";
        if (bookName.equals("")){
            model.addAttribute("bookNameMsg","Book name cannot be empty!");
            return "manager/book_edit";
        }
        else if (price<=0){
            model.addAttribute("priceMsg","Please enter a correct price!");
            return "manager/book_edit";
        }
        else if (author.equals("")){
            model.addAttribute("authorMsg","Author cannot be empty!");
            return "manager/book_edit";
        }
        else if (!saleCount.toString().matches(quantityReg)){
            model.addAttribute("saleCountMsg","Please enter a correct saleCount!");
            return "manager/book_edit";
        }
        else if (!bookCount.toString().matches(quantityReg)){
            model.addAttribute("bookCountMsg","Please enter a correct bookCount!");
            return "manager/book_edit";
        }
        else if (category.equals("")){
            model.addAttribute("categoryMsg","Book category cannot be empty!");
            return "manager/book_add";
        }

        Book book = (Book) session.getAttribute("book");
        book.setBookName(bookName);
        book.setPrice(price);
        book.setAuthor(author);
        book.setSaleCount(saleCount);
        book.setBookCount(bookCount);
        book.setBookStatus(bookStatus);
        book.setCategory(category);

        bookService.updateBook(book);
        return "redirect:/toEditSuccessfulPage";
    }

    @RequestMapping("/toEditSuccessfulPage")
    public String toEditSuccessfulPage(){
        return "manager/edit_successful";
    }




}
