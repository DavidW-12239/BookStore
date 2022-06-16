package org.bookStore.controller;

import org.bookStore.pojo.Book;
import org.bookStore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class BookController {
    @Autowired
    BookService bookService;

    @RequestMapping("/book")
    public String index(HttpSession session){
        List<Book> bookList = bookService.getBookList();
        session.setAttribute("bookList",bookList);
        return "index";
    }

    @PostMapping("/addBook")
    public String addBook(@RequestParam("bookName") String bookName, @RequestParam("price") Double price,
                          @RequestParam("author") String author, @RequestParam("saleCount") Integer saleCount,
                          @RequestParam("bookCount") Integer bookCount, HttpSession session, Model model){
        if (bookName.equals("")){
            model.addAttribute("bookNameMsg","Book name cannot be empty!");
            return "manager/book_edit";
        }
        else if (price<=0){
            model.addAttribute("priceMsg","Please enter the right price!");
            return "manager/book_edit";
        }
        else if (author.equals("")){
            model.addAttribute("authorMsg","Author cannot be empty!");
            return "manager/book_edit";
        }
        else if (saleCount<0){
            model.addAttribute("saleCountMsg","Please enter the right saleCount!");
            return "manager/book_edit";
        }
        else if (bookCount<0){
            model.addAttribute("bookCountMsg","Please enter the right bookCount!");
            return "manager/book_edit";
        }

        bookService.addBook(new Book(null, null, bookName, price, author, saleCount, bookCount, 0));
        List<Book> bookList = bookService.getBookList();
        session.setAttribute("bookList",bookList);
        return "redirect:/toAddingSuccessfulPage";
    }

    @RequestMapping("/toAddingSuccessfulPage")
    public String toAddingSuccessfulPage(){
        return "manager/adding_successful";
    }


}
