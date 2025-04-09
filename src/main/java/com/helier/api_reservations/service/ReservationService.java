package com.helier.api_reservations.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import com.helier.api_reservations.connector.CatalogConnector;
import com.helier.api_reservations.connector.response.CityDTO;
import com.helier.api_reservations.dto.ReservationDTO;
import com.helier.api_reservations.dto.SegementDTO;
import com.helier.api_reservations.enums.APIError;
import com.helier.api_reservations.exceotion.ApiException;
import com.helier.api_reservations.model.Reservation;
import com.helier.api_reservations.repository.ReservationRepository;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

@Service
public class ReservationService {
    private ReservationRepository reservationRepository;

    private ConversionService conversionService;

    private CatalogConnector catalogConnector;

    public ReservationService(ReservationRepository repository, ConversionService conversionService,
            CatalogConnector catalogConnector) {
        this.reservationRepository = repository;
        this.conversionService = conversionService;
        this.catalogConnector = catalogConnector;
    }

    public List<ReservationDTO> getAllReservations() {
        return conversionService.convert(reservationRepository.findAll(), List.class);
    }

    public ReservationDTO getReservationById(Long id) {
        Optional<Reservation> reservation = reservationRepository.findById(id);

        if (reservation.isEmpty()) {
            throw new ApiException(APIError.RESERVATION_NOT_FOUND);
        }

        ReservationDTO test = conversionService.convert(reservation.get(), ReservationDTO.class);
        return test;
    }

    public ReservationDTO createReservation(ReservationDTO reservation) {
        if (Objects.nonNull(reservation.getId())) {
            throw new ApiException(APIError.RESERVATION_WITH_SAME_ID);
        }

        checkCity(reservation);
        Reservation transformed = conversionService.convert(reservation, Reservation.class);
        validateEntity(transformed);
        Reservation createdReservation = reservationRepository.save(Objects.requireNonNull(transformed));
        return conversionService.convert(createdReservation, ReservationDTO.class);

    }

    public ReservationDTO updateReservation(Long id, ReservationDTO reservation) {
        if (getReservationById(id) == null) {
            throw new ApiException(APIError.RESERVATION_NOT_FOUND);
        }

        checkCity(reservation);
        Reservation transformed = conversionService.convert(reservation, Reservation.class);
        validateEntity(transformed);
        Reservation updatedReservation = reservationRepository.save(Objects.requireNonNull(transformed));
        return conversionService.convert(updatedReservation, ReservationDTO.class);
    }

    public void deleteReservation(Long id) {
        if (getReservationById(id) == null) {
            throw new ApiException(APIError.RESERVATION_NOT_FOUND);
        }
        reservationRepository.deleteById(id);
    }

    private void checkCity(ReservationDTO reservation) {
        for (SegementDTO segment : reservation.getItinerary().getSegments()) {
            CityDTO origin = catalogConnector.getCity(segment.getOrigin());
            CityDTO destination = catalogConnector.getCity(segment.getDestination());

            if (origin == null || destination == null) {
                throw new ApiException(APIError.VALIDATION_ERROR);
            } else {
                System.out.println("City found: " + origin.getCity_name() + " - " + destination.getCity_name());
            }
        }
    }

    public void validateEntity(Reservation transformed) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Reservation>> violations = validator.validate(transformed);
        if (!violations.isEmpty()) {
           throw new ConstraintViolationException(violations);
        }
    }
}
