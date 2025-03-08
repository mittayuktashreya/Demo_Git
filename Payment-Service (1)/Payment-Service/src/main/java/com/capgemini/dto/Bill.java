package com.capgemini.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public class Bill {

    @NotNull(message = "Bill Id cannot be empty")
    @Positive(message = "It must be always positive")
    private Long billId; // Unique identifier for each bill.
    @NotNull(message = "Bill Date cannot be empty")
    private LocalDate billDate;
    @NotNull(message = "Bill Due Date cannot be empty")
    private LocalDate billDueDate;
    @NotNull(message = "Units Consumed cannot be empty")
    private double unitsConsumed;
    @NotNull(message = "Bill Amount cannot be empty")
    private double billAmount;

    public double getBillAmount() {
        return billAmount;
    }

    public void setBillAmount(double billAmount) {

        this.billAmount = billAmount;
    }

    public LocalDate getBillDate() {

        return billDate;
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
    public Long getBillId() {
        return billId;
    }

    public void setBillId(Long billId) {
        this.billId = billId;
    }

    public double getUnitsConsumed() {
        return unitsConsumed;
    }

    public void setUnitsConsumed(double unitsConsumed) {
        this.unitsConsumed = unitsConsumed;
    }
}
