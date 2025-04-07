package com.helier.api_reservations.mapper;

import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

import com.helier.api_reservations.dto.ReservationDTO;
import com.helier.api_reservations.model.Reservation;

@Mapper(componentModel = "spring")
public interface ReservationDTOMapper extends Converter<ReservationDTO, Reservation> {

    @Override
    Reservation convert(ReservationDTO source);

}