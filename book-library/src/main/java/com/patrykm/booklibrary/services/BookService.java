package com.patrykm.booklibrary.services;

import com.patrykm.booklibrary.domain.Author;
import com.patrykm.booklibrary.domain.Book;
import com.patrykm.booklibrary.domain.Hire;
import com.patrykm.booklibrary.dto.BookDto;
import com.patrykm.booklibrary.repository.AuthorRepository;
import com.patrykm.booklibrary.repository.BookRepository;
import com.patrykm.booklibrary.repository.HireRepository;
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

    @Autowired
    HireRepository hireRepository;

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

    public BookDto convert(Book book){
        if(book == null)
            return null;
        BookDto bookDto = new BookDto();
        bookDto.setId(book.getId());
        bookDto.setTitle(book.getTitle());
        bookDto.setYear(book.getYear());
        bookDto.setPublisher(book.getPublisher());
        bookDto.setIsbn(book.getIsbn());
        bookDto.setAuthorName(book.getAuthor().getName());

        List<Hire> hires = hireRepository.findBookByIdNoGiveBack(book.getId());

        bookDto.setHireStatus(hires.size() > 0);
        if(hires.size() > 0)
            bookDto.setGiveBackDate(hires.get(0).getPlannedGiveBackDate()); //weź pierwszy element z kolekcji, tylko będzie jeden

        return bookDto;
    }

    public List<BookDto> convert(List<Book> books){
        if(books == null)
            return null;

        List<BookDto> bookDtoList = new ArrayList<>();
        for(Book book : books)
            bookDtoList.add(convert(book));

        return bookDtoList;

    }
}
