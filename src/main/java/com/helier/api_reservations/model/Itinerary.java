package com.helier.api_reservations.model;

import java.util.List;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.Valid;

@Entity
public class Itinerary extends Base {

    @Valid
    @OneToMany
    @Cascade(CascadeType.ALL)
    @JoinColumn(name = "itinerary_id")
    private List<Segment> segments;

    @Valid
    @OneToOne
    @Cascade(CascadeType.ALL)
    @JoinColumn(name = "itinerary_id")
    private Price price;

    public List<Segment> getSegments() {
        return segments;
    }

    public void setSegments(List<Segment> segments) {
        this.segments = segments;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId() .hashCode());
        result = prime * result + ((segments == null) ? 0 : segments.hashCode());
        result = prime * result + ((price == null) ? 0 : price.hashCode());
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
        Itinerary other = (Itinerary) obj;
        if (getId() == null) {
            if (other.getId() != null)
                return false;
        } else if (!getId().equals(other.getId()))
            return false;
        if (segments == null) {
            if (other.segments != null)
                return false;
        } else if (!segments.equals(other.segments))
            return false;
        if (price == null) {
            if (other.price != null)
                return false;
        } else if (!price.equals(other.price))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Itinerary [id=" + getId() + ", segments=" + segments + ", price=" + price + "]";
    }
}
