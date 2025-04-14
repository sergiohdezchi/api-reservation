package com.helier.api_reservations.dto;

import java.time.LocalDate;

public class SearchReservationCriteriaDTO {
    private Long itineraryId;
    private String firstName;
    private String lastName;
    private LocalDate reeservationDate;


    private String sortField;
    private String sortingDirection;

    private Integer currentPage = 0;
    private Integer pageSize = 10;

    public SearchReservationCriteriaDTO(Long itineraryId, String firstName, String lastName,
            LocalDate reeservationDate) {
        this.itineraryId = itineraryId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.reeservationDate = reeservationDate;
    }

    public Long getItineraryId() {
        return itineraryId;
    }

    public void setItineraryId(Long itineraryId) {
        this.itineraryId = itineraryId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getReeservationDate() {
        return reeservationDate;
    }

    public void setReeservationDate(LocalDate reeservationDate) {
        this.reeservationDate = reeservationDate;
    }

    public String getSortField() {
        return sortField;
    }

    public void setSortField(String sortField) {
        this.sortField = sortField;
    }

    public String getSortingDirection() {
        return sortingDirection;
    }

    public void setSortingDirection(String sortingDirection) {
        this.sortingDirection = sortingDirection;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    
}
