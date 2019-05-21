package com.patrykm.booklibrary.repository;

import com.patrykm.booklibrary.domain.Author;
import com.patrykm.booklibrary.domain.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collection;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookRepositoryTest {
    private static final Integer BOOK_ID_EXISTS = 2;
    private static final Integer BOOK_ID_NOT_EXISTS = 3;

    @Autowired
    BookRepository bookRepository;

    @Test
    public void isBookRepositoryNotNull(){
        assertNotNull(bookRepository);
    }

    @Test
    public void getBooksTest(){
        Collection<Book> books = bookRepository.getBooks();
        assertNotNull(books);
        assertFalse(books.isEmpty());

        for(Book book : books)
            validBook(book);
    }

    @Test
    public void getBookById(){
        Book bookExist = bookRepository.getBook(BOOK_ID_EXISTS);
        assertNotNull(bookExist);
        validBook(bookExist);

        Book bookNotExists = bookRepository.getBook(BOOK_ID_NOT_EXISTS);
        assertNull(bookNotExists);
    }

    @Test
    public void saveBookTest(){
        int bookCount = bookRepository.getBooks().size();
        bookRepository.saveBook(null);
        assertEquals(bookCount,bookRepository.getBooks().size());

        bookRepository.saveBook(getBookTemplate());
        assertEquals(bookCount + 1,bookRepository.getBooks().size());

    }

    @Test
    public void updateBookTest(){
        Book book = bookRepository.getBook(BOOK_ID_EXISTS);
        assertNotNull(book);

        book.setYear(2100);
        book.setTitle("Test2100");

        bookRepository.updateBook(book);
        Book bookModified = bookRepository.getBook(BOOK_ID_EXISTS);
        assertEquals(bookModified.getTitle(),"Test2100");
        assertEquals(bookModified.getYear(),new Integer(2100));
        //assertEquals(book,bookModified);

    }

    public void validBook(Book book){
        assertNotNull(book.getId());
        assertTrue(book.getId() >=0);
        assertNotNull(book.getTitle());
        assertTrue(book.getTitle().length() >= 2);
    }

    public Book getBookTemplate(){
        Book book = new Book();
        book.setTitle("TESTTEST");
        book.setIsbn("TESTTEST");
        book.setPublisher("TESTTEST");
        //book.setAuthor(new Author("TEST"));
        book.setYear(2000);
        return book;
    }
}
