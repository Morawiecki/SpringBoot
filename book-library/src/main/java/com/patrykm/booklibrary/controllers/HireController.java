package com.patrykm.booklibrary.controllers;

import com.patrykm.booklibrary.domain.Book;
import com.patrykm.booklibrary.domain.Hire;
import com.patrykm.booklibrary.domain.User;
import com.patrykm.booklibrary.dto.BookDto;
import com.patrykm.booklibrary.dto.UserDto;
import com.patrykm.booklibrary.services.BookService;
import com.patrykm.booklibrary.services.HireService;
import com.patrykm.booklibrary.services.PaymentService;
import com.patrykm.booklibrary.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.math.BigDecimal;
import java.util.List;

@Controller
public class HireController {

    @Autowired
    BookService bookService;

    @Autowired
    HireService hireService;

    @Autowired
    UserService userService;

    @Autowired
    PaymentService paymentService;

    @RequestMapping(value = "/books/hires/{id}",method = RequestMethod.GET)
    public String getHires(Model model, @PathVariable("id") Integer id){
        Book book = bookService.getBook(id);
        List<Hire> hires = hireService.getHiresByBookId(id);
        model.addAttribute("book",book);
        model.addAttribute("hires", hires);
        return "hires";
    }

    @RequestMapping(value = "/books/hire/{id}",method = RequestMethod.GET)
    public String hire(Model model,@PathVariable("id") Integer id){
        Hire hire = hireService.hire(id,userService.getLoggedUser());
        //List<Book> books = bookService.getBooks();
        List<BookDto> books = bookService.convert(bookService.getBooks());
        //User loggedUser = userService.getLoggedUser();
        UserDto loggedUser = userService.convert(userService.getLoggedUser());
        model.addAttribute("books",books);
        model.addAttribute("user",loggedUser);
        model.addAttribute("hireStatus",hire != null);

        if(hire != null)
            model.addAttribute("giveBackDate",hire.getPlannedGiveBackDate());
        return "books";

    }

    @RequestMapping(value = "/user/hires",method = RequestMethod.GET)
    public String loggedUserHires(Model model){
        User loggedUser = userService.getLoggedUser();
        UserDto loggedUserDto = userService.convert(loggedUser);
        List<Hire> hires = hireService.getHireListByUserId(loggedUser.getId());
        BigDecimal payment = paymentService.getPaymentSumByUser(loggedUser.getId());
        BigDecimal penalty = paymentService.getPenaltySumByUser(loggedUser.getId());

        model.addAttribute("user",loggedUserDto);
        model.addAttribute("hires",hires);
        model.addAttribute("payment",payment);
        model.addAttribute("penalty",penalty);

        return "hires-own";

    }

    @RequestMapping(value = "/admin/hires",method = RequestMethod.GET)
    public String notGiveBackHires(Model model){
        User loggedUser = userService.getLoggedUser();
        UserDto loggedUserDto = userService.convert(loggedUser);
        List<Hire> hires = hireService.getNotGiveBackHireList();

        model.addAttribute("user",loggedUserDto);
        model.addAttribute("hires",hires);
        model.addAttribute("showMessage", Boolean.FALSE);


        return "hires-admin";

    }

    @RequestMapping(value = "/admin/hires/giveback/{id}",method = RequestMethod.GET)
    public String giveBack(Model model, @PathVariable("id") Long id){
        User loggedUser = userService.getLoggedUser();
        UserDto loggedUserDto = userService.convert(loggedUser);

        hireService.setHireAsGiveBack(id);
        String bookName = hireService.getHireById(id).getHiredBook().getTitle();
        List<Hire> hires = hireService.getNotGiveBackHireList();

        model.addAttribute("user",loggedUserDto);
        model.addAttribute("hires",hires);
        model.addAttribute("showMessage", Boolean.TRUE);
        model.addAttribute("bookName", bookName);

        return "hires-admin";

    }


}
