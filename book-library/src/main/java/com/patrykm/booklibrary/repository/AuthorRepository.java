package com.patrykm.booklibrary.repository;

import com.patrykm.booklibrary.domain.Author;
import com.patrykm.booklibrary.domain.Book;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Collection;

@Repository
public class AuthorRepository<author> {

    @PersistenceContext
    private EntityManager em;

    public Author getAuthor(int id) {
        return em.find(Author.class, id);
    }

    @Transactional
    public void saveAuthor(Author author) {
            if(author !=null)
            em.persist(author);
    }

    @Transactional
    public void updateAuthor(Author author) {
        if(author !=null)
            em.merge(author);
    }

    @Transactional
    public void removeAuthor(Author author){
        if(author != null)
            em.remove(author);

    }
}
