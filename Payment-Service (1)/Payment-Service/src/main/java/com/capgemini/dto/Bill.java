package com.capgemini.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class Bill {

    @NotNull(message = "{bill.id.notnull}")
    @Positive(message = "{bill.id.positive}")
    private Long billId;

    @NotNull(message = "{bill.date.notnull}")
    private LocalDate billDate;

    @NotNull(message = "{bill.due.notnull}")
    private LocalDate billDueDate;

    @NotNull(message = "{bill.amount.notnull}")
    private double billAmount;

    @Valid
    @NotNull(message = "{bill.reading.notnull}")
    private Reading billForReading;

    public Reading getBillForReading() {
        return (billForReading != null) ? billForReading : new Reading();
    }

    // Getters and Setters

    public void setBillId(Long billId) {
        this.billId = billId;
    }

    public Long getBillId() {
        return billId;
    }

    public void setBillDate(LocalDate billDate) {
        this.billDate = billDate;
    }

    public LocalDate getBillDueDate() {
        return billDueDate;
    }

    public void setBillDueDate(LocalDate billDueDate) {
        this.billDueDate = billDueDate;
    }

    public double getBillAmount() {
        return billAmount;
    }

    public void setBillAmount(double billAmount) {
        this.billAmount = billAmount;
    }

    public void setBillForReading(Reading billForReading) {
        this.billForReading = billForReading;
    }
}
