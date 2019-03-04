package com.patrykm.booklibrary.services;

import com.patrykm.booklibrary.domain.Author;
import com.patrykm.booklibrary.domain.Book;
import com.patrykm.booklibrary.repository.AuthorRepository;
import com.patrykm.booklibrary.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {
    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorRepository authorRepository;

    public List<Book> getBooks(){
        return new ArrayList<>(bookRepository.getBooks());
    }

    public void saveBook(Book book){
        if(book != null) {
            System.out.println("Zapisuję książkę o id: " + book.getId());
            boolean bookExists = bookRepository.getBook(book.getId()) != null;

            if(bookExists) {
                authorRepository.updateAuthor(book.getAuthor());
                bookRepository.updateBook(book);
            }
            else {
                authorRepository.saveAuthor(book.getAuthor());
                bookRepository.saveBook(book);
            }
        }
    }

    public void deleteBook(int id) {
        Book bookToRemove = bookRepository.getBook(id);
        Author authorToRemove = bookToRemove.getAuthor();
        bookRepository.deleteBook(bookToRemove);
        authorRepository.removeAuthor(authorToRemove);
    }

    public Book getNewBook(){
        Book newBook = new Book();
        newBook.setAuthor(new Author());
        return newBook;
    }

    public Book getBook(int id){
       return bookRepository.getBook(id);
    }

    public List<Book> getBookByAuthor (String authorName) {
        if(authorName != null){
            return new ArrayList<>(bookRepository.getBooksByAuthor(authorName));
        } else
            return null;
    }

    public List<Book> getBooks(Integer year, String publisher, String isbn) {
        return new ArrayList<>(bookRepository.getBooks(year,publisher,isbn));
    }

    public List<Book> getBookByTitle (String title) {
        if(title != null){
            return new ArrayList<>(bookRepository.getBooksByTitle(title));
        } else
            return null;
    }
}
