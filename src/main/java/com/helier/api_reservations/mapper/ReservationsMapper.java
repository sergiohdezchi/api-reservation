package com.helier.api_reservations.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

import com.helier.api_reservations.dto.ReservationDTO;
import com.helier.api_reservations.model.Reservation;

@Mapper(componentModel = "spring")
public interface ReservationsMapper extends Converter<List<Reservation>, List<ReservationDTO>> {
    @Override
    List<ReservationDTO> convert(List<Reservation> reservations);

}
