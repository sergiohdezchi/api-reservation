package com.helier.api_reservations.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Price extends Base {

    @Column(name = "total_price", nullable = false)
    private BigDecimal totalPrice;

    @Column(name = "total_tax", nullable = false)
    private BigDecimal totalTax;

    @Column(name = "base_price", nullable = false)
    private BigDecimal basePrice;

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public BigDecimal getTotalTax() {
        return totalTax;
    }

    public void setTotalTax(BigDecimal totalTax) {
        this.totalTax = totalTax;
    }

    public BigDecimal getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(BigDecimal basePrice) {
        this.basePrice = basePrice;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((totalPrice == null) ? 0 : totalPrice.hashCode());
        result = prime * result + ((totalTax == null) ? 0 : totalTax.hashCode());
        result = prime * result + ((basePrice == null) ? 0 : basePrice.hashCode());
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
        Price other = (Price) obj;
        if (getId() == null) {
            if (other.getId() != null)
                return false;
        } else if (!getId().equals(other.getId()))
            return false;
        if (totalPrice == null) {
            if (other.totalPrice != null)
                return false;
        } else if (!totalPrice.equals(other.totalPrice))
            return false;
        if (totalTax == null) {
            if (other.totalTax != null)
                return false;
        } else if (!totalTax.equals(other.totalTax))
            return false;
        if (basePrice == null) {
            if (other.basePrice != null)
                return false;
        } else if (!basePrice.equals(other.basePrice))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Price [id=" + getId() + ", totalPrice=" + totalPrice + ", totalTax=" + totalTax + ", basePrice="
                + basePrice + "]";
    }
}
