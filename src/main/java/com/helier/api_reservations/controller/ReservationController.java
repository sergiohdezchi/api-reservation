package com.helier.api_reservations.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.helier.api_reservations.controller.resource.ReservationResource;
import com.helier.api_reservations.dto.ReservationDTO;
import com.helier.api_reservations.exceotion.ApiException;
import com.helier.api_reservations.service.ReservationService;

import io.github.resilience4j.ratelimiter.RequestNotPermitted;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@Validated
@RestController
@RequestMapping("/reservation")
public class ReservationController implements ReservationResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReservationController.class);

    private ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping
    public ResponseEntity<List<ReservationDTO>> getReservations() {
        LOGGER.info("Obtain all the reservations");
        List<ReservationDTO> response = reservationService.getAllReservations();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservationDTO> getReservationById(@Min(1) @PathVariable long id) {
        LOGGER.info("Obtain information from a reservation with {}", id);
        ReservationDTO response = reservationService.getReservationById(id);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    @RateLimiter(name = "post-reservation", fallbackMethod = "postReservationFallback")
    public ResponseEntity<ReservationDTO> createReservation(@Valid @RequestBody ReservationDTO reservation) {
        LOGGER.info("Saving new reservation");
        ReservationDTO response = reservationService.createReservation(reservation);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReservationDTO> updateReservation(@Min(1) @PathVariable long id,
            @Valid @RequestBody ReservationDTO reservation) {
        LOGGER.info("Updating a reservation with {}", id);
        ReservationDTO response = reservationService.updateReservation(id, reservation);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReservation(@Min(1) @PathVariable long id) {
        LOGGER.info("Deleting a reservation with {}", id);
        reservationService.deleteReservation(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<ReservationDTO> postReservationFallback(ReservationDTO reservation, RequestNotPermitted ex) {
        LOGGER.debug("calling to fallbackPost");
        throw new ApiException(HttpStatus.TOO_MANY_REQUESTS, "Rate limit exceeded for postReservation",
                List.of(ex.getMessage()));
    }
}
