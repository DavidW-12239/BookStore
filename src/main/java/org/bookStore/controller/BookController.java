package org.bookStore.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.bookStore.pojo.Book;
import org.bookStore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class BookController {
    @Autowired
    BookService bookService;

    @RequestMapping("/toAddBookPage")
    public String toAddBookPage(){
        return "manager/book_add";
    }

    @PostMapping("/addBook")
    public String addBook(@RequestParam("bookName") String bookName, @RequestParam("price") Double price,
                          @RequestParam("author") String author, @RequestParam("saleCount") Integer saleCount,
                          @RequestParam("bookCount") Integer bookCount, HttpSession session, Model model){
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

        bookService.addBook(new Book(null, null, bookName, price, author, saleCount, bookCount, 0));
        return "redirect:/toAddingSuccessfulPage";
    }

    @RequestMapping("/toAddingSuccessfulPage")
    public String toAddingSuccessfulPage(){
        return "manager/adding_successful";
    }

    @RequestMapping("/getMainBookList")
    public String getMainBookList(@RequestParam(required=false, name="price1") Double price1, @RequestParam(required=false, name="price2") Double price2,
            @RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10") Integer pageSize, Model model){
        //not a paging search
        //import pageHelper
        //invoke pageHelper, page number and the size of each page
        PageHelper.startPage(pageNum, pageSize);
        //then the paging search
        if (price1==null){
            price1=0.0;
        }
        if(price2==null){
            price2=1000.0;
        }
        List<Book> bookList = bookService.getBookListByPrice(price1, price2);
        //use pageInfo to package the result
        //packaging detailed paging info and search result, 5 pages show each time
        PageInfo pageInfo = new PageInfo(bookList, 5);
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

        return "index";
    }

    @RequestMapping("/getDiscountBookList")
    public String getDiscountBookList(@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "4") Integer pageSize, Model model){
        //not a paging search
        //import pageHelper
        //invoke pageHelper, page number and the size of each page
        PageHelper.startPage(pageNum, pageSize);
        //then the paging search
        List<Book> bookList = bookService.getBookListByStatus(1);
        //use pageInfo to package the result
        //packaging detailed paging info and search result, 5 pages show each time
        PageInfo page = new PageInfo(bookList, 3);
        model.addAttribute("page", page);
        return "index";
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
        PageInfo pageInfo = new PageInfo(bookList, 5);
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
                          @RequestParam("bookCount") Integer bookCount, HttpSession session, Model model){
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

        Book book = (Book) session.getAttribute("book");
        book.setBookName(bookName);
        book.setPrice(price);
        book.setAuthor(author);
        book.setSaleCount(saleCount);
        book.setBookCount(bookCount);

        bookService.updateBook(book);
        return "redirect:/toEditSuccessfulPage";
    }

    @RequestMapping("/toEditSuccessfulPage")
    public String toEditSuccessfulPage(){
        return "manager/edit_successful";
    }




}
