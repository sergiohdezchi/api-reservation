package com.helier.api_reservations.mapper;

import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

import com.helier.api_reservations.dto.ReservationDTO;
import com.helier.api_reservations.model.Reservation;

@Mapper(componentModel = "spring")
public interface ReservationMapper extends Converter<Reservation, ReservationDTO> {

    @Override
    ReservationDTO convert(Reservation source);

}