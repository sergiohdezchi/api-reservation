package com.helier.api_reservations.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.transaction.annotation.Transactional;

import com.helier.api_reservations.model.Reservation;

import jakarta.persistence.LockModeType;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Transactional(timeout = 10)
    @Lock(LockModeType.PESSIMISTIC_READ)
    List<Reservation> findAll(Specification<Reservation> specification, Pageable pageable);
}
