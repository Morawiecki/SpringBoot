package com.patrykm.booklibrary.domain;

import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
//@Table(name = "book")

//@Component
//@Scope("prototype") //zasięg prototype - wiele instancji w kontekście; zasięg singletone - jedna instancja
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    //@Column(name = "bookTitle")

    @NotNull
    @Size(min = 2, message = "Tytuł musi posiadać co najmniej 2 litery")
    private String title;

    @NotNull
    @Range(min = 1, max = 9999, message = "Rok wydania musi być z przedziału 1 - 9999")
    private int year;

    @NotNull
    @Size(min = 1, message = "Wydawca musi posiadać co najmniej 1 znak")
    private String publisher;

    @NotNull
    @Size(min = 6, message = "ISBN musi posiadać co najmnie 6 znaków")
    private String isbn;

    @OneToOne
    @Valid
    private Author author;

    public Book(){

    }

    public Book(String title, int year, String publisher, String isbn, Author author) {
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

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Book{" + "title='" + title + '\'' + ", year=" + year + ", publisher='" + publisher + '\'' + ", isbn='" + isbn + '\'' + '}';
    }
}
