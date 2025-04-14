package com.helier.api_reservations.dao;

import java.util.List;
import java.util.Optional;

import com.helier.api_reservations.dto.SearchReservationCriteriaDTO;
import com.helier.api_reservations.model.Reservation;

public interface ReservationDao {
    List<Reservation> findAll(SearchReservationCriteriaDTO criteria);

    Optional<Reservation> findById(Long id);

    Reservation save(Reservation reservation);

    void deleteById(Long id);

    boolean existsById(Long id);
}
