package org.bookStore.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.bookStore.pojo.Book;
import org.bookStore.service.BookService;
import org.bookStore.service.CartItemService;
import org.bookStore.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Controller
public class BookController {
    @Autowired
    BookService bookService;

    @Autowired
    CartItemService cartItemService;

    @PostMapping("/addBook")
    public String addBook(@RequestParam(required=false, name="bookName") String bookName, @RequestParam("currPrice") Double currPrice,
                          @RequestParam("origPrice") Double origPrice, @RequestParam("author") String author,
                          @RequestParam("saleCount") Integer saleCount, @RequestParam("bookCount") Integer bookCount,
                          @RequestParam("bookStatus") Integer bookStatus, @RequestParam("category") String category,
                          Model model, HttpSession session) throws IOException {
        String quantityReg = "^[1-9]\\d*$";
        if (currPrice==null){
            model.addAttribute("currPriceMsg","Please enter a current price!");
            return "manager/book_add";
        }
        else if (origPrice==null){
            model.addAttribute("origPriceMsg","Please enter an original price!");
            return "manager/book_add";
        }
        else if (bookName.equals("")){
            model.addAttribute("bookNameMsg","Book name cannot be empty!");
            return "manager/book_add";
        }
        else if (currPrice<=0){
            model.addAttribute("currPriceMsg","Please enter a correct price!");
            return "manager/book_add";
        }
        else if (origPrice<=0 || (currPrice>origPrice && bookStatus==0) || (currPrice<origPrice && bookStatus==0) || (currPrice>=origPrice && bookStatus==1)){
            model.addAttribute("origPriceMsg","Please enter a correct price!");
            return "manager/book_add";
        }
        else if (author.equals("")){
            model.addAttribute("authorMsg","Author cannot be empty!");
            return "manager/book_add";
        }
        else if (!saleCount.toString().matches(quantityReg) || saleCount==null){
            model.addAttribute("saleCountMsg","Please enter a correct saleCount!");
            return "manager/book_add";
        }
        else if (!bookCount.toString().matches(quantityReg) || bookCount==null){
            model.addAttribute("bookCountMsg","Please enter a correct bookCount!");
            return "manager/book_add";
        }
        else if (category.equals("")){
            model.addAttribute("categoryMsg","Book category cannot be empty!");
            return "manager/book_add";
        }
/*        else if (photo==null){
            model.addAttribute("photoMsg","Please upload the photo!");
            return "manager/book_add";
        }*/
        //String bookImg = Utils.UploadPhoto(photo, session);
        bookService.addBook(new Book(null, null, bookName, currPrice, origPrice,
                author, saleCount, bookCount, bookStatus, category, 0, 0.0));
        return "redirect:/toAddingSuccessfulPage";
    }

    @RequestMapping("/toAddingSuccessfulPage")
    public String toAddingSuccessfulPage(){
        return "manager/adding_successful";
    }

    @RequestMapping("/getBookByCategory")
    public String getBookByCategory(@RequestParam("category") String category, HttpSession session){
        if (session.getAttribute("category") == null){
            session.setAttribute("category", category);
        }else if(!session.getAttribute("category").equals(category)) {
            session.setAttribute("category", category);
        }else if (session.getAttribute("category").equals(category)){
            session.setAttribute("category", null);
        }
        return "redirect:/getMainBookList";
    }

    @RequestMapping("/getDiscountBook")
    public String getDiscountBook(HttpSession session){
        if (session.getAttribute("bookStatus") == null){
            session.setAttribute("bookStatus", 1);
        } else {
            session.setAttribute("bookStatus", null);
        }
        return "redirect:/getMainBookList";
    }

    @RequestMapping("/getMainBookList")
    public String getMainBookList(@RequestParam(required=false, name="price1") Double price1,
                                  @RequestParam(required=false, name="price2") Double price2,
                                  @RequestParam(required=false) String bookName,
            @RequestParam(defaultValue = "1") Integer pageNum, Model model, HttpSession session){

        session.setAttribute("cartCount", session.getAttribute("cartCount"));

        //import pageHelper
        //invoke pageHelper, page number and the size of each page

        //then the paging search
        if (price1!=null ){
            session.setAttribute("price1", price1);
        }
        if (price2!=null){
            session.setAttribute("price2", price2);
        }
        if (bookName!=null){
            session.setAttribute("bookName", bookName);
        }
        String category = "";
        if (session.getAttribute("category")!=null){
            category = (String) session.getAttribute("category");
        }
        Integer bookStatus = null;
        if (session.getAttribute("bookStatus")!=null){
            bookStatus = (Integer) session.getAttribute("bookStatus");
        }

        //use pageInfo to package the result
        //packaging detailed paging info and search result, 5 pages show each time
        PageHelper.startPage(pageNum, 10);
        //then the paging search
        List<Book> bookList = bookService.getAllBookList(price1, price2, bookName, category, bookStatus);
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
        session.setAttribute("price1", null);
        session.setAttribute("price2", null);
        session.setAttribute("bookName", null);
        session.setAttribute("category", null);
        session.setAttribute("bookStatus", null);
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
    public String editBook(@RequestParam("bookName") String bookName, @RequestParam("currPrice") Double currPrice,
                           @RequestParam("origPrice") Double origPrice,
                          @RequestParam("author") String author, @RequestParam("saleCount") Integer saleCount,
                          @RequestParam("bookCount") Integer bookCount, @RequestParam("bookStatus") Integer bookStatus,
                          @RequestParam("category") String category, HttpSession session, Model model){
        String quantityReg = "^[1-9]\\d*$";
        if (bookName.equals("")){
            model.addAttribute("bookNameMsg","Book name cannot be empty!");
            return "manager/book_edit";
        }
        else if (currPrice<=0){
            model.addAttribute("currPriceMsg","Please enter a correct current price!");
            return "manager/book_edit";
        }
        else if (origPrice<=0 || (currPrice>origPrice && bookStatus==0) || (currPrice<origPrice && bookStatus==0) || (currPrice>=origPrice && bookStatus==1)){
            model.addAttribute("origPriceMsg","Please enter a correct original price!");
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
            return "manager/book_edit";
        }

        Book book = (Book) session.getAttribute("book");
        book.setBookName(bookName);
        book.setCurrPrice(currPrice);
        book.setOrigPrice(origPrice);
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

    /*   @Value("${user.file.path}")
    private String filePath;
    @Value("${user.file.name}")
    private String fileName;

    @RequestMapping("/upload")
    public ModelAndView update(@RequestParam("pic") MultipartFile multipartFile) {

        try {
            // ????????????
            File file = new File(filePath + multipartFile.getOriginalFilename());
            multipartFile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");

        return modelAndView;
    }

    @RequestMapping(value = "/{filename:.+}")
    @ResponseBody
    public ResponseEntity<?> getFile(@PathVariable String filename) {
        try {
            return ResponseEntity.ok(resourceLoader.getResource("file:" + Paths.get(filePath + filename)));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }*/


}
