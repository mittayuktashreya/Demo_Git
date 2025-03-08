package com.capgemini.service;

import com.capgemini.dto.Bill;
import com.capgemini.dto.PaymentDTO;
import com.capgemini.dto.PaymentResponse;
import com.capgemini.exception.NoSuchCustomerException;
import com.capgemini.model.Payment;
import com.capgemini.model.PaymentStatus;
import com.capgemini.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Random;

@Service
public class PaymentServiceImpl implements IPaymentService{

    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public PaymentResponse payBill(PaymentDTO paymentDTO, Bill bill) {
        if (bill == null || bill.getBillId() == null) {
            throw new IllegalArgumentException("Bill or Bill ID cannot be null");
        }

        // Set billId automatically from Bill
        paymentDTO.setBillId(bill.getBillId());

        // Calculate total amount to be paid
        double finalAmount = bill.getBillAmount();
        double lateFee = 0.0;

        // Apply late fee if payment is late
        if (paymentDTO.getPaymentDate().isAfter(bill.getBillDueDate())) {
            lateFee = 100.0;
            paymentDTO.setLatePaymentCharges(lateFee);
            finalAmount += lateFee;
        } else {
            paymentDTO.setLatePaymentCharges(0.0);
        }

        paymentDTO.setTotalPaid(finalAmount);

        // Assign random payment status
        PaymentStatus[] statuses = PaymentStatus.values();
        PaymentStatus randomStatus = statuses[new Random().nextInt(statuses.length)];
        paymentDTO.setStatus(randomStatus);

        // Save the payment in the database
        paymentRepository.save(convertToEntity(paymentDTO));

        // ✅ **Invoking PaymentResponse here**
        return new PaymentResponse(randomStatus, lateFee, finalAmount);
    }

@Override
public PaymentStatus cancelPayment(Long paymentId) throws NoSuchCustomerException {
    Payment payment = paymentRepository.findById(paymentId)
            .orElseThrow(() -> new NoSuchCustomerException("Payment not found for ID: " + paymentId));
    paymentRepository.deleteById(paymentId);
    return PaymentStatus.FAILED;
}

@Override
public PaymentStatus updatePayment(Long paymentId, PaymentDTO paymentDTO) throws NoSuchCustomerException {
        System.out.println("Received request to update payment with ID: " + paymentId);
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new NoSuchCustomerException("Payment not found for ID: " + paymentId));        System.out.println("Payment found: " + payment);
        // Update payment details
        payment.setPaymentDate(paymentDTO.getPaymentDate());
        payment.setPaymentMode(paymentDTO.getPaymentMode());
        payment.setLatePaymentCharges(paymentDTO.getLatePaymentCharges());
        payment.setTotalPaid(paymentDTO.getTotalPaid());
        payment.setStatus(paymentDTO.getStatus());

        System.out.println("Updated payment details: " + payment);
        paymentRepository.save(payment);
        System.out.println("Payment successfully updated!");
        return payment.getStatus();
    }



@Override
public PaymentDTO getPaymentDetails(Long paymentId) throws NoSuchCustomerException {
    Payment payment = paymentRepository.findById(paymentId)
            .orElseThrow(() -> new NoSuchCustomerException("Payment not found for ID: " + paymentId));
    return convertToDTO(payment);
}


@Override
public List<PaymentDTO> viewAllPayments() {
    List<Payment> payments = paymentRepository.findAll();
    return payments.stream()
            .map(this::convertToDTO)
            .toList();
}

public PaymentDTO convertToDTO(Payment payment) {
    PaymentDTO paymentDTO = new PaymentDTO();
    paymentDTO.setPaymentId(payment.getPaymentId()); // Ensure paymentId is set
    paymentDTO.setBillId(payment.getBillId());
    paymentDTO.setPaymentDate(payment.getPaymentDate());
    paymentDTO.setPaymentMode(payment.getPaymentMode());
    paymentDTO.setStatus(payment.getStatus());
    paymentDTO.setTotalPaid(payment.getTotalPaid());
    paymentDTO.setLatePaymentCharges(payment.getLatePaymentCharges());
    return paymentDTO;
}

public Payment convertToEntity(PaymentDTO paymentDTO) {
    Payment payment = new Payment();
    payment.setBillId(paymentDTO.getBillId());
    payment.setPaymentDate(paymentDTO.getPaymentDate());
    payment.setPaymentMode(paymentDTO.getPaymentMode());
    payment.setStatus(paymentDTO.getStatus());
    payment.setTotalPaid(paymentDTO.getTotalPaid());
    payment.setLatePaymentCharges(paymentDTO.getLatePaymentCharges());
    return payment;
}
}