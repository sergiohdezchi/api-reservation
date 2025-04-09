package com.helier.api_reservations.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.helier.api_reservations.model.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

}
