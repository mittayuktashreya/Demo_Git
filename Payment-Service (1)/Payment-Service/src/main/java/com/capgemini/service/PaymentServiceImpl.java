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
        List<Payment> existingPayments = paymentRepository.findByBillId(bill.getBillId());
        boolean alreadyPaid = existingPayments.stream()
                .anyMatch(payment -> payment.getStatus() == PaymentStatus.SUCCESS);

        if (alreadyPaid) {
            throw new IllegalStateException("Payment already done for Bill ID: " + bill.getBillId());
        }
        paymentDTO.setBillId(bill.getBillId());
        double finalAmount = bill.getBillAmount();
        double lateFee = 0.0;
        if (bill.getBillForReading() != null) { // ✅ Fetch Reading details
            int units = bill.getBillForReading().getUnitsConsumed();
            int pricePerUnit = bill.getBillForReading().getPricePerUnits();
            finalAmount = units * pricePerUnit; // ✅ Recalculate amount
        }
        if (paymentDTO.getPaymentDate().isAfter(bill.getBillDueDate())) {
            lateFee = 100.0;
            paymentDTO.setLatePaymentCharges(lateFee);
            finalAmount += lateFee;
        } else {
            paymentDTO.setLatePaymentCharges(0.0);
        }
        paymentDTO.setTotalPaid(finalAmount);

        paymentDTO.setStatus(PaymentStatus.SUCCESS);
        paymentRepository.save(convertToEntity(paymentDTO));
        return new PaymentResponse(PaymentStatus.SUCCESS, lateFee, finalAmount);
    }

        @Override
    public PaymentStatus cancelPayment(Long paymentId) throws NoSuchCustomerException {
        Payment payment = paymentRepository.findById(paymentId)
            .orElseThrow(() -> new NoSuchCustomerException("Payment not found for ID: " + paymentId));

    System.out.println("Deleting payment with ID: " + paymentId);
    paymentRepository.deleteById(paymentId);
    System.out.println("Payment successfully deleted!");
    return PaymentStatus.FAILED;
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
    return payments.stream().map(this::convertToDTO).toList();
    }

    @Override
    public List<PaymentDTO> getPaymentsByStatus(PaymentStatus status) {
        List<Payment> payments = paymentRepository.findByStatus(status);
        return payments.stream().map(this::convertToDTO).toList();
    }
    public PaymentDTO convertToDTO(Payment payment) {
        PaymentDTO paymentDTO = new PaymentDTO();
        paymentDTO.setPaymentId(payment.getPaymentId());
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