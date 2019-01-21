package com.patrykm.booklibrary;

import com.patrykm.booklibrary.domain.Book;
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


    @Override
    public void run(String... args) throws Exception {
        /*System.out.println(book);
        System.out.println(test);
        System.out.println(test1);
        System.out.println(size);
        System.out.println(book2);
        System.out.println(book == book2);*/

    }

    /*@Autowired
    public void setBook2(Book book2) {
        this.book2 = book2;
    }*/
}
