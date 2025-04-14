package com.helier.api_reservations.service.specification;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.helier.api_reservations.dto.SearchReservationCriteriaDTO;
import com.helier.api_reservations.model.Reservation;
import jakarta.persistence.criteria.Predicate;

public class ReservationSpecification {

    public static Specification<Reservation> withSearchCriteria(SearchReservationCriteriaDTO criteria) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (criteria.getItineraryId() != null) {
                predicates.add(criteriaBuilder.equal(root.get("itinerary").get("id"), criteria.getItineraryId()));
            }
            if (criteria.getFirstName() != null && !criteria.getFirstName().isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("passengers").get("firstName"), criteria.getFirstName()));
            }
            if (criteria.getLastName() != null && !criteria.getLastName().isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("passengers").get("lastName"), criteria.getLastName()));
            }
            if (criteria.getReeservationDate() != null) {
                predicates.add(criteriaBuilder.equal(root.get("creationDate"), criteria.getReeservationDate()));
            }
            if (criteria.getSortingDirection() != null && criteria.getSortField() != null) {
                if (criteria.getSortingDirection().equalsIgnoreCase("desc")) {
                   query.orderBy(criteriaBuilder.desc(root.get(criteria.getSortField())));
                } else {
                   query.orderBy(criteriaBuilder.asc(root.get(criteria.getSortField())));
                }
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
