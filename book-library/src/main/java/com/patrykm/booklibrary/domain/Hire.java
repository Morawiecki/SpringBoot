package com.patrykm.booklibrary.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Hire {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date hireDate;
    private Date plannedGiveBackDate;
    private Date realGiveBackDate;

    @OneToOne
    private User hireUser;

    @ManyToOne
    @JoinColumn(name = "bookId")
    private Book hiredBook;

    public Long getId() {
        return id;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public Date getPlannedGiveBackDate() {
        return plannedGiveBackDate;
    }

    public Date getRealGiveBackDate() {
        return realGiveBackDate;
    }

    public User getHireUser() {
        return hireUser;
    }

    public Book getHiredBook() {
        return hiredBook;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public void setPlannedGiveBackDate(Date plannedGiveBackDate) {
        this.plannedGiveBackDate = plannedGiveBackDate;
    }

    public void setRealGiveBackDate(Date realGiveBackDate) {
        this.realGiveBackDate = realGiveBackDate;
    }

    public void setHireUser(User hireUser) {
        this.hireUser = hireUser;
    }

    public void setHiredBook(Book hiredBook) {
        this.hiredBook = hiredBook;
    }
}
