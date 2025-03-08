package com.capgemini.dto;

import com.capgemini.model.PaymentMode;
import com.capgemini.model.PaymentStatus;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public class PaymentDTO {

    private Long paymentId;
    private Long billId;

    @NotNull(message = "Payment Mode is required")
    private PaymentMode paymentMode;

    @NotNull(message = "Payment Date is required")
   //@PastOrPresent
    private LocalDate paymentDate;

   // @NotNull(message = "Late Payment Charges can be in the range 0-1000")
    private double latePaymentCharges;

    //@NotNull(message = "Total Paid is required")
    private double totalPaid;

    private PaymentStatus status;

    public Long getBillId() {
        return billId;
    }

    public void setBillId(Long billId) {
        this.billId = billId;
    }

    public double getLatePaymentCharges() {
        return latePaymentCharges;
    }

    public void setLatePaymentCharges(double latePaymentCharges) {
        this.latePaymentCharges = latePaymentCharges;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }

    public PaymentMode getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(PaymentMode paymentMode) {
        this.paymentMode = paymentMode;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

    public double getTotalPaid() {
        return totalPaid;
    }

    public void setTotalPaid(double totalPaid) {
        this.totalPaid = totalPaid;
    }
}