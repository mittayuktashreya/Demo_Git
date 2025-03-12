package com.capgemini.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.time.LocalDate;

public class Reading {

    @NotNull(message = "{reading.id.notnull}")
    @Positive(message = "{reading.id.positive}")
    private Long readingId;

    @NotNull(message = "{reading.units.notnull}")
    @Positive(message = "{reading.units.positive}")
    private int unitsConsumed;

    @NotNull(message = "{reading.photo.notnull}")
    private String readingPhoto;

    @NotNull(message = "{reading.date.notnull}")
    private LocalDate readingDate;

    @NotNull(message = "{reading.price.notnull}")
    @Positive(message = "{reading.price.positive}")
    private int pricePerUnits;

    // Getters and Setters

    public Long getReadingId() {
        return readingId;
    }

    public void setReadingId(Long readingId) {
        this.readingId = readingId;
    }

    public int getUnitsConsumed() {
        return unitsConsumed;
    }

    public void setUnitsConsumed(int unitsConsumed) {
        this.unitsConsumed = unitsConsumed;
    }

    public String getReadingPhoto() {
        return readingPhoto;
    }

    public void setReadingPhoto(String readingPhoto) {
        this.readingPhoto = readingPhoto;
    }

    public LocalDate getReadingDate() {
        return readingDate;
    }

    public void setReadingDate(LocalDate readingDate) {
        this.readingDate = readingDate;
    }

    public int getPricePerUnits() {
        return pricePerUnits;
    }

    public void setPricePerUnits(int pricePerUnits) {
        this.pricePerUnits = pricePerUnits;
    }
}
