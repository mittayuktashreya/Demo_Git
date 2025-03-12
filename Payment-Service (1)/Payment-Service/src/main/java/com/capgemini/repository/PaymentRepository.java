package com.capgemini.repository;

import com.capgemini.model.Payment;
import com.capgemini.model.PaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment,Long> {
    List<Payment> findByBillId(Long billId);
    List<Payment> findByStatus(PaymentStatus status);
}
