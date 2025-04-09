package com.helier.api_reservations.model;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.helier.api_reservations.listener.ReservationEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.Valid;

import jakarta.validation.constraints.NotEmpty;

@Entity
@EntityListeners(ReservationEntityListener.class)
public class Reservation extends Base {

    @Valid
    @NotEmpty(message = "You must have at least one passenger")
    @OneToMany
    @Cascade(CascadeType.ALL)
    @JoinColumn(name = "reservation_id")
    private List<Passenger> passengers;

    @Valid
    @NotEmpty(message = "You must have at least one itinerary")
    @ManyToOne
    @Cascade(CascadeType.ALL)
    @JoinColumn(name = "itinerary_id")
    private Itinerary itinerary;

    @Column(name = "creation_date")
    private LocalDate creationDate;

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<Passenger> passengers) {
        this.passengers = passengers;
    }

    public Itinerary getItinerary() {
        return itinerary;
    }

    public void setItinerary(Itinerary itinerary) {
        this.itinerary = itinerary;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((passengers == null) ? 0 : passengers.hashCode());
        result = prime * result + ((itinerary == null) ? 0 : itinerary.hashCode());
        result = prime * result + ((creationDate == null) ? 0 : creationDate.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Reservation other = (Reservation) obj;
        if (getId() == null) {
            if (other.getId() != null)
                return false;
        } else if (!getId().equals(other.getId()))
            return false;
        if (passengers == null) {
            if (other.passengers != null)
                return false;
        } else if (!passengers.equals(other.passengers))
            return false;
        if (itinerary == null) {
            if (other.itinerary != null)
                return false;
        } else if (!itinerary.equals(other.itinerary))
            return false;
        if (creationDate == null) {
            if (other.creationDate != null)
                return false;
        } else if (!creationDate.equals(other.creationDate))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Reservation [id=" + getId() + ", passengers=" + passengers + ", itinerary=" + itinerary + ", creationDate="
                + creationDate + "]";
    }
}
