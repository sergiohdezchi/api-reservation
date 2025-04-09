package com.helier.api_reservations.listener;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helier.api_reservations.model.Reservation;

import jakarta.persistence.PostLoad;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PrePersist;

public class ReservationEntityListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(ReservationEntityListener.class);

    @PrePersist
    public void prePersist(Reservation reservation) {
        LOGGER.info("PrePersist: {}", reservation);
        reservation.setCreationDate(LocalDate.now());
    }

    @PostPersist
    public void postPersist(Reservation reservation) {
        LOGGER.info("PostPersist: {}", reservation);
    }

    @PostLoad
    public void postLoad(Reservation reservation) {
        LOGGER.info("PostLoad: {}", reservation);
    }
}
