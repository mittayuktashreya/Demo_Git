package com.capgemini.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public class PaymentBillRequest {

    @Valid
    @NotNull(message = "{payment.details.notnull}")
    private PaymentDTO paymentDTO;

    @Valid
    @NotNull(message = "{bill.details.notnull}")
    private Bill bill;

    // Getters and Setters

    public PaymentDTO getPaymentDTO() {
        return paymentDTO;
    }

    public void setPaymentDTO(PaymentDTO paymentDTO) {
        this.paymentDTO = paymentDTO;
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }
}
