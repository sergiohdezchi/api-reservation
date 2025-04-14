package com.helier.api_reservations.controller.resource;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.helier.api_reservations.dto.ErrorDTO;
import com.helier.api_reservations.dto.ReservationDTO;
import com.helier.api_reservations.dto.SearchReservationCriteriaDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;

@Tag(name = "Reservation", description = "Reservation API")
public interface ReservationResource {
    @Operation(description = "Get the information of all the reservations", responses = {
            @ApiResponse(responseCode = "200", description = "Return the information of all the reservations", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = List.class))),
            @ApiResponse(responseCode = "500", description = "Something bad happens to obtain th reservations", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorDTO.class))), })
    public ResponseEntity<List<ReservationDTO>> getReservations(SearchReservationCriteriaDTO criteria);

    @Operation(description = "Get the information about one reservation", responses = {
            @ApiResponse(responseCode = "200", description = "Return the information of one reservation", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ReservationDTO.class))),

            @ApiResponse(responseCode = "404", description = "Reservation not found", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorDTO.class))),

            @ApiResponse(responseCode = "500", description = "Something bad happens to obtain th reservations", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorDTO.class))) }, parameters = {
                    @Parameter(in = ParameterIn.PATH, name = "id", description = "Id of the reservation to search", example = "1") })
    public ResponseEntity<ReservationDTO> getReservationById(@Min(1) @PathVariable long id);

    @Operation(description = "Create one reservation", responses = {
            @ApiResponse(responseCode = "200", description = "Return the created reservation", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ReservationDTO.class))),

            @ApiResponse(responseCode = "400", description = "Bad request of the information to persist", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorDTO.class))),

            @ApiResponse(responseCode = "500", description = "Something bad happens to obtain th reservations", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorDTO.class))) }, requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(examples = @ExampleObject(name = "Reservation", summary = "Example reservation to create", value = "{\n"
                    + "    \"passengers\": [\n" + "        {\n" + "            \"firstName\": \"Andres\",\n"
                    + "            \"lastName\": \"Sacco\",\n" + "            \"documentNumber\": \"12345678\",\n"
                    + "            \"documentType\": \"DNI\",\n" + "            \"birthday\": \"1985-01-01\"\n"
                    + "        }\n" + "    ],\n" + "    \"itinerary\": {\n" + "        \"segment\": [\n"
                    + "            {\n" + "                \"origin\": \"BUE\",\n"
                    + "                \"destination\": \"MIA\",\n" + "                \"departure\": \"2024-12-31\",\n"
                    + "                \"arrival\": \"2025-01-01\",\n" + "                \"carrier\": \"AA\"\n"
                    + "            }\n" + "        ],\n" + "        \"price\": {\n" + "            \"totalPrice\": 1,\n"
                    + "            \"totalTax\": 0,\n" + "            \"basePrice\": 1\n" + "        }\n" + "    }\n"
                    + "}"))))
    public ResponseEntity<ReservationDTO> createReservation(@Valid @RequestBody ReservationDTO reservation);

    @Operation(description = "Update one reservation", responses = {
            @ApiResponse(responseCode = "200", description = "Return the updated reservation", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ReservationDTO.class))),

            @ApiResponse(responseCode = "400", description = "Bad request of the information to persist", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorDTO.class))),

            @ApiResponse(responseCode = "404", description = "Reservation not found", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorDTO.class))),

            @ApiResponse(responseCode = "500", description = "Something bad happens to obtain th reservations", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorDTO.class))) }, parameters = {
                    @Parameter(in = ParameterIn.PATH, name = "id", description = "Id of the reservation to update", example = "1") }, requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(examples = @ExampleObject(name = "Reservation", summary = "Example reservation to create", value = "{\n"
                            + "  \"id\": 1,\n" + "  \"passengers\": [\n" + "    {\n"
                            + "      \"firstName\": \"Andres\",\n" + "      \"lastName\": \"Sacco\",\n"
                            + "      \"documentNumber\": \"12345678\",\n" + "      \"documentType\": \"DNI\",\n"
                            + "      \"birthday\": \"1985-01-01\"\n" + "    }\n" + "  ],\n" + "  \"itinerary\": {\n"
                            + "    \"segment\": [\n" + "      {\n" + "        \"origin\": \"EZE\",\n"
                            + "        \"destination\": \"MIA\",\n" + "        \"departure\": \"2024-12-31\",\n"
                            + "        \"arrival\": \"2025-01-01\",\n" + "        \"carrier\": \"AA\"\n" + "      }\n"
                            + "    ],\n" + "    \"price\": {\n" + "      \"totalPrice\": 1,\n"
                            + "      \"totalTax\": 0,\n" + "      \"basePrice\": 1\n" + "    }\n" + "  }\n" + "}"))))
    public ResponseEntity<ReservationDTO> updateReservation(@Min(1) @PathVariable long id,
            @Valid @RequestBody ReservationDTO reservation);

    @Operation(description = "Delete one reservation", responses = {
            @ApiResponse(responseCode = "200", description = "Return nothing", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Void.class))),

            @ApiResponse(responseCode = "404", description = "Reservation not found", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorDTO.class))),

            @ApiResponse(responseCode = "500", description = "Something bad happens to obtain th reservations", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorDTO.class))) }, parameters = {
                    @Parameter(in = ParameterIn.PATH, name = "id", description = "Id of the reservation to delete", example = "1") })
    public ResponseEntity<Void> deleteReservation(@Min(1) @PathVariable long id);
}
