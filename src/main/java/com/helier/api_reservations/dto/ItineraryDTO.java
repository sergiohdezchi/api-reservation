package com.helier.api_reservations.dto;

import java.util.List;

import jakarta.validation.Valid;

public class ItineraryDTO {
    @Valid
    private List<SegementDTO> segments;
    private PriceDTO price;

    public PriceDTO getPrice() {
        return price;
    }

    public void setPrice(PriceDTO price) {
        this.price = price;
    }

    public List<SegementDTO> getSegments() {
        return segments;
    }

    public void setSegments(List<SegementDTO> segments) {
        this.segments = segments;
    }
}
