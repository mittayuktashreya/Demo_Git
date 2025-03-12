package com.capgemini.dto;

import com.capgemini.model.PaymentStatus;

public class PaymentResponse {
    private PaymentStatus status;
    private double latePaymentCharges;
    private double totalAmountPaid;

    // Constructor
    public PaymentResponse(PaymentStatus status, double latePaymentCharges, double totalAmountPaid) {
        this.status = status;
        this.latePaymentCharges = latePaymentCharges;
        this.totalAmountPaid = totalAmountPaid;
    }

    // Getters and Setters
    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

    public double getLatePaymentCharges() {
        return latePaymentCharges;
    }

    public void setLatePaymentCharges(double latePaymentCharges) {
        this.latePaymentCharges = latePaymentCharges;
    }

    public double getTotalAmountPaid() {
        return totalAmountPaid;
    }

    public void setTotalAmountPaid(double totalAmountPaid) {
        this.totalAmountPaid = totalAmountPaid;
    }
}
