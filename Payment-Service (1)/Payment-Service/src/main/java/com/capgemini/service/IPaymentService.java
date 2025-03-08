package com.capgemini.service;

import com.capgemini.dto.Bill;
import com.capgemini.dto.PaymentDTO;
import com.capgemini.dto.PaymentResponse;
import com.capgemini.exception.NoSuchCustomerException;
import com.capgemini.model.Payment;
import com.capgemini.model.PaymentStatus;

import java.util.List;

public interface IPaymentService {

    PaymentResponse payBill(PaymentDTO paymentDTO, Bill bill);

    PaymentStatus cancelPayment(Long paymentId) throws NoSuchCustomerException;

    PaymentStatus updatePayment(Long paymentId, PaymentDTO paymentDTO) throws NoSuchCustomerException;

    PaymentDTO getPaymentDetails(Long paymentId) throws NoSuchCustomerException;

    List<PaymentDTO> viewAllPayments();

}
