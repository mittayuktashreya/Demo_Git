package com.capgemini.controller;

import com.capgemini.dto.Bill;
import com.capgemini.dto.PaymentBillRequest;
import com.capgemini.dto.PaymentDTO;
import com.capgemini.dto.PaymentResponse;
import com.capgemini.exception.NoSuchCustomerException;
import com.capgemini.model.PaymentStatus;
import com.capgemini.service.IPaymentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/payment")
@Validated
public class PaymentController {

    //  Call the service method, which returns PaymentResponse


    @Autowired
    private IPaymentService paymentService;

    @PostMapping(value = "/payBill")
    public ResponseEntity<PaymentResponse> payBill(@Valid @RequestBody PaymentBillRequest paymentBillRequest) {
        PaymentResponse response = paymentService.payBill(paymentBillRequest.getPaymentDTO(), paymentBillRequest.getBill());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/allPayments")
    public ResponseEntity<List<PaymentDTO>> viewAllPayments() {
        List<PaymentDTO> payments = paymentService.viewAllPayments();
        return new ResponseEntity<>(payments, HttpStatus.OK);
    }

    @DeleteMapping(value = "/cancel/{paymentId}")
    public ResponseEntity<PaymentStatus> cancelPayment(@PathVariable Long paymentId) throws NoSuchCustomerException {
        PaymentStatus status = paymentService.cancelPayment(paymentId);
        return new ResponseEntity<>(status, HttpStatus.OK);
    }

    @PutMapping(value = "/update/{paymentId}")
    public ResponseEntity<PaymentStatus> updatePayment(
          /*  @PathVariable Long paymentId,
            @Valid @RequestBody PaymentDTO paymentDTO) throws NoSuchCustomerException {
        PaymentStatus status = paymentService.updatePayment(paymentId, paymentDTO);
        return new ResponseEntity<>(status, HttpStatus.OK);*/
            @PathVariable Long paymentId,
            @RequestBody PaymentDTO paymentDTO) throws NoSuchCustomerException {
        PaymentStatus status = paymentService.updatePayment(paymentId, paymentDTO);
        return ResponseEntity.ok(status);
    }

    @GetMapping(value = "/details/{paymentId}")
    public ResponseEntity<PaymentDTO> getPaymentDetails(@PathVariable Long paymentId) throws NoSuchCustomerException {
        PaymentDTO paymentDTO = paymentService.getPaymentDetails(paymentId);
        return new ResponseEntity<>(paymentDTO, HttpStatus.OK);
    }
}