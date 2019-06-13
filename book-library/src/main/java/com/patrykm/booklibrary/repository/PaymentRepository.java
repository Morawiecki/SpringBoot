package com.patrykm.booklibrary.repository;

import com.patrykm.booklibrary.domain.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    List<Payment> findByUser_Id(Integer id);

    @Query("SELECT SUM(amount) FROM Payment p WHERE p.user.id=:id")
    BigDecimal sumPaymentByUser(@Param("id") Integer id);
}
