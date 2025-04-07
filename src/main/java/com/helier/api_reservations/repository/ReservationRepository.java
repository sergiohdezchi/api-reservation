package com.helier.api_reservations.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.helier.api_reservations.model.Itinerary;
import com.helier.api_reservations.model.Passenger;
import com.helier.api_reservations.model.Price;
import com.helier.api_reservations.model.Reservation;
import com.helier.api_reservations.model.Segment;

@Repository
public class ReservationRepository {
    static List<Reservation> reservations = new ArrayList<>();

    static {
        Passenger passenger1 = new Passenger();
        passenger1.setId(1L);
        passenger1.setFirstName("John");
        passenger1.setLastName("Doe");
        passenger1.setDocumentType("DNI");
        passenger1.setDocumentNumber("12345678");
        passenger1.setBirthday(LocalDate.of(1990, 1, 1));

        Price price = new Price();
        price.setId(1L);
        price.setBasePrice(BigDecimal.ONE);
        price.setTotalPrice(BigDecimal.ONE);
        price.setTotalTax(BigDecimal.ZERO);

        Segment segment = new Segment();
        segment.setId(1L);
        segment.setDeparture("2024-12-31");
        segment.setArrival("2025-01-01");
        segment.setOrigin("LIM");
        segment.setDestination("MIA");
        segment.setCarrier("AA");

        Itinerary itinerary = new Itinerary();
        itinerary.setId(1L);
        itinerary.setPrice(price);
        itinerary.setSegments(List.of(segment));

        Reservation reservation = new Reservation();
        reservation.setId(1L);
        reservation.setPassengers(List.of(passenger1));
        reservation.setItinerary(itinerary);

        reservations.add(reservation);
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public Optional<Reservation> getReservationById(Long id) {
        return reservations.stream().filter(reservation -> reservation.getId().equals(id)).findFirst();
    }

    public Reservation saveReservation(Reservation reservation) {
        reservation.setId((long) (reservations.size() + 1));
        reservations.add(reservation);
        return reservation;
    }

    public Reservation updateReservation(Long id, Reservation reservation) {
        Optional<Reservation> existingReservation = getReservationById(id);
        Reservation updatedReservation = existingReservation.get();
        updatedReservation.setId(reservation.getId());
        updatedReservation.setPassengers(reservation.getPassengers());
        updatedReservation.setItinerary(reservation.getItinerary());
        return updatedReservation;
    }

    public void deleteReservation(Long id) {
        reservations.removeIf(reservation -> reservation.getId().equals(id));
    }
}
