package com.capgemini.dto;

import com.capgemini.model.PaymentMode;
import com.capgemini.model.PaymentStatus;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public class PaymentDTO {

    private Long paymentId;

    private Long billId;

    @NotNull(message = "{payment.mode.notnull}")
    private PaymentMode paymentMode;

    @NotNull(message = "{payment.date.notnull}")
    private LocalDate paymentDate;

    private Double latePaymentCharges;
    private Double totalPaid;
    private PaymentStatus status;

    // Getters and Setters

    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }

    public Long getBillId() {
        return billId;
    }

    public void setBillId(Long billId) {
        this.billId = billId;
    }

    public PaymentMode getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(PaymentMode paymentMode) {
        this.paymentMode = paymentMode;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Double getLatePaymentCharges() {
        return latePaymentCharges;
    }

    public void setLatePaymentCharges(double latePaymentCharges) {
        this.latePaymentCharges = latePaymentCharges;
    }

    public Double getTotalPaid() {
        return totalPaid;
    }

    public void setTotalPaid(double totalPaid) {
        this.totalPaid = totalPaid;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }
}
