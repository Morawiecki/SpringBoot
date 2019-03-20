package com.patrykm.booklibrary.repository;

import com.patrykm.booklibrary.domain.Hire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface HireRepository extends JpaRepository<Hire, Long> {
    /*
    @Override
    List<Hire> findAll();

    @Override
    Optional<Hire> findById(Long aLong);
    */

    //@Query("SELECT h FROM Hire h WHERE")
    List<Hire> findByHiredBook_Id(Integer id);

}
