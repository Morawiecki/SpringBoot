package com.patrykm.booklibrary.domain;

import javax.persistence.*;

@Entity
//@Table(name = "book")

//@Component
//@Scope("prototype") //zasięg prototype - wiele instancji w kontekście; zasięg singletone - jedna instancja
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    //@Column(name = "bookTitle")
    private String title;
    private int year;
    private String publisher;
    private String isbn;
    private String author;

    public Book(){

    }

    public Book(String title, int year, String publisher, String isbn, String author) {
        this.title = title;
        this.year = year;
        this.publisher = publisher;
        this.isbn = isbn;
        this.author = author;
    }

    /*
    public Book(){
        this.title = "Ogniem i mieczem";
        this.year = new Random().nextInt(2000);
        this.publisher = "Wydawca XYZ";
        this.isbn = "AZ4515GF4";
    }
    */

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Book{" + "title='" + title + '\'' + ", year=" + year + ", publisher='" + publisher + '\'' + ", isbn='" + isbn + '\'' + '}';
    }
}
