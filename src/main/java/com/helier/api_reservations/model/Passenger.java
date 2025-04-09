package com.helier.api_reservations.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

@Entity
public class Passenger extends Base {

    @Size(min = 2, max = 30, message = "First name must be between 2 and 30 characters")
    @NotBlank(message = "First name cannot be blank")
    @Column(name = "first_name", nullable = false, length = 30)
    private String firstName;

    @Size(min = 2, max = 30, message = "Last name must be between 2 and 30 characters")
    @NotBlank(message = "Last name cannot be blank")
    @Column(name = "last_name", nullable = false, length = 30)
    private String lastName;

    @Column(name = "document_number", nullable = false)
    private String documentNumber;

    @Column(name = "document_type", nullable = false)
    private String documentType;

    @Past(message = "Birthday must be in the past")
    @Column(name = "birthday", nullable = false)
    private LocalDate birthday;

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

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
        result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
        result = prime * result + ((documentNumber == null) ? 0 : documentNumber.hashCode());
        result = prime * result + ((documentType == null) ? 0 : documentType.hashCode());
        result = prime * result + ((birthday == null) ? 0 : birthday.hashCode());
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
        Passenger other = (Passenger) obj;
        if (getId() == null) {
            if (other.getId() != null)
                return false;
        } else if (!getId().equals(other.getId()))
            return false;
        if (firstName == null) {
            if (other.firstName != null)
                return false;
        } else if (!firstName.equals(other.firstName))
            return false;
        if (lastName == null) {
            if (other.lastName != null)
                return false;
        } else if (!lastName.equals(other.lastName))
            return false;
        if (documentNumber == null) {
            if (other.documentNumber != null)
                return false;
        } else if (!documentNumber.equals(other.documentNumber))
            return false;
        if (documentType == null) {
            if (other.documentType != null)
                return false;
        } else if (!documentType.equals(other.documentType))
            return false;
        if (birthday == null) {
            if (other.birthday != null)
                return false;
        } else if (!birthday.equals(other.birthday))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Passenger [id=" + getId() + ", firstName=" + firstName + ", lastName=" + lastName + ", documentNumber="
                + documentNumber + ", documentType=" + documentType + ", birthday=" + birthday + "]";
    }
}
