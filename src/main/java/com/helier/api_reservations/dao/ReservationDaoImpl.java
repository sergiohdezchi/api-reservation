package com.helier.api_reservations.dao;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.support.TransactionTemplate;

import com.helier.api_reservations.dto.SearchReservationCriteriaDTO;
import com.helier.api_reservations.model.Reservation;
import com.helier.api_reservations.service.specification.ReservationSpecification;

import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class ReservationDaoImpl implements ReservationDao {

    @Autowired
    private TransactionTemplate transactionTemplate;

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Reservation> findAll(SearchReservationCriteriaDTO criteria) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Reservation> query = criteriaBuilder.createQuery(Reservation.class);
        Root<Reservation> root = query.from(Reservation.class);

        Predicate predicate = ReservationSpecification.withSearchCriteria(criteria).toPredicate(root, query,
                criteriaBuilder);
        query.where(predicate);

        return entityManager.createQuery(query).getResultList();
    }

    @Override
        //@Lock(LockModeType.PESSIMISTIC_READ)
    public Optional<Reservation> findById(Long id) {
        Reservation reservation = entityManager.find(Reservation.class, id, LockModeType.PESSIMISTIC_READ);

        return Optional.of(reservation);
    }

    @Override
    public Reservation save(Reservation reservation) {

        transactionTemplate.execute(status -> {
            try {
                if (Objects.isNull(reservation.getId())) {
                    entityManager.persist(reservation);
                    entityManager.flush();
                } else {
                    entityManager.merge(reservation);
                    entityManager.flush();
                }
            } catch (Exception e) {
                status.setRollbackOnly();
                throw e;
            }

            return null;
        });

        return reservation;
    }

    @Override
    public void deleteById(Long id) {
        Reservation reservation = entityManager.find(Reservation.class, id);
        if (Objects.nonNull(reservation)) {
            entityManager.remove(reservation);
            entityManager.flush();
        }
    }

    @Override
    public boolean existsById(Long id) {
        Reservation reservation = entityManager.find(Reservation.class, id);
        return Objects.nonNull(reservation);
    }

}
