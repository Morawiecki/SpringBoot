package com.patrykm.booklibrary.controllers;

import com.patrykm.booklibrary.domain.Book;
import com.patrykm.booklibrary.domain.Hire;
import com.patrykm.booklibrary.domain.User;
import com.patrykm.booklibrary.services.BookService;
import com.patrykm.booklibrary.services.HireService;
import com.patrykm.booklibrary.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class HireController {

    @Autowired
    BookService bookService;

    @Autowired
    HireService hireService;

    @Autowired
    UserService userService;

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
        Hire hire = hireService.hire(id);
        List<Book> books = bookService.getBooks();
        User loggedUser = userService.getLoggedUser();
        model.addAttribute("books",books);
        model.addAttribute("user",loggedUser);
        model.addAttribute("hireStatus",hire != null);

        if(hire != null)
            model.addAttribute("giveBackDate",hire.getPlannedGiveBackDate());
        return "books";

    }
}