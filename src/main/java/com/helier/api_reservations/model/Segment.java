package com.helier.api_reservations.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Segment extends Base {

    @Column(name = "origin", nullable = false)
    private String origin;

    @Column(name = "destination", nullable = false)
    private String destination;

    @Column(name = "departure", nullable = false)
    private String departure;

    @Column(name = "arrival", nullable = false)
    private String arrival;

    @Column(name = "carrier", nullable = false)
    private String carrier;

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((origin == null) ? 0 : origin.hashCode());
        result = prime * result + ((destination == null) ? 0 : destination.hashCode());
        result = prime * result + ((departure == null) ? 0 : departure.hashCode());
        result = prime * result + ((arrival == null) ? 0 : arrival.hashCode());
        result = prime * result + ((carrier == null) ? 0 : carrier.hashCode());
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
        Segment other = (Segment) obj;
        if (getId() == null) {
            if (other.getId() != null)
                return false;
        } else if (!getId().equals(other.getId()))
            return false;
        if (origin == null) {
            if (other.origin != null)
                return false;
        } else if (!origin.equals(other.origin))
            return false;
        if (destination == null) {
            if (other.destination != null)
                return false;
        } else if (!destination.equals(other.destination))
            return false;
        if (departure == null) {
            if (other.departure != null)
                return false;
        } else if (!departure.equals(other.departure))
            return false;
        if (arrival == null) {
            if (other.arrival != null)
                return false;
        } else if (!arrival.equals(other.arrival))
            return false;
        if (carrier == null) {
            if (other.carrier != null)
                return false;
        } else if (!carrier.equals(other.carrier))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Segment [id=" + getId() + ", origin=" + origin + ", destination=" + destination + ", departure=" + departure
                + ", arrival=" + arrival + ", carrier=" + carrier + "]";
    }
}
