package com.patrykm.booklibrary;

import com.patrykm.booklibrary.domain.Author;
import com.patrykm.booklibrary.domain.Book;
import com.patrykm.booklibrary.repository.BookRepository;
import com.patrykm.booklibrary.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:custom.properties")
public class AppStarter implements CommandLineRunner {

    /*@Autowired
    Book book;

    //wstrzyknięcie przez seter
    Book book2;

    //wstrzyknięcie wartości
    @Value("22")
    Integer test;

    @Value("23")
    int test1;

    @Value("${spring.pagesize:25}") //wartość awaryjna
    Integer size;*/

    @Autowired
    //BookRepository bookRepository;
    BookService bookService;

    @Override
    public void run(String... args) throws Exception {
        /*System.out.println(book);
        System.out.println(test);
        System.out.println(test1);
        System.out.println(size);
        System.out.println(book2);
        System.out.println(book == book2);*/
        init();

    }

    public void init(){
        Book book = new Book("W pustyni",1951,"PWN","kjhkh344346",new Author("Mieczysław Bączek"));
        //bookRepository.saveBook(book);
        bookService.saveBook(book);

        Book book2 = new Book("I w puszczy",1952,"PWN","kjhkh344347",new Author("Wiesław Pajączek"));
        bookService.saveBook(book2);

        Book book3 = new Book("I w kamieniołomach",1952,"PWN","kjhkh344347",new Author("Lesław Ciem"));
        bookService.saveBook(book3);

        Book book4 = new Book("I w mleczarni",1991,"PWN","TYR5825847",new Author("Henryk Sienkiewicz"));
        bookService.saveBook(book4);

        Book book5 = new Book("Mleczna z kaszanką - nowoczesna kuchnia",1999,"PWN","YTY1484848",new Author("Henryk Sienkiewicz"));
        bookService.saveBook(book5);
    }

    /*@Autowired
    public void setBook2(Book book2) {
        this.book2 = book2;
    }*/
}
