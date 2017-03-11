package com.bookingsystem.entities;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Created by vijay on 2/27/17.
 */
@Entity
@Table(name = "CUSTOMER")
public class Customer implements BaseEntity, Comparable<Customer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private final Long id;

    @Column(name = "FIRST_NAME")
    private final String firstName;

    @Column(name = "LAST_NAME")
    private final String lastName;

    @Column(name = "DATE_OF_BIRTH")
    private final LocalDate dob;

    @Column(name = "GENDER")
    private final String gender;

    @Column( name = "EMAIL_ID")
    private final String email;

    @Column(name = "ADDRESS_LINE_1")
    private final String addressLine1;

    @Column(name = "ADDRESS_LINE_2")
    private final String addressLine2;

    @Column(name = "STATE")
    private final String state;

    @Column(name = "COUNTRY")
    private final String country;

    @Column(name = "ZIP_CODE")
    private final String zipCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (name = "RESERVATION")
    private final Reservation reservation;


    private Customer(Long id, String firstName, String lastName, LocalDate dob, String gender, String addressLine1, String addressLine2, String state, String country, String zipCode, String email, Reservation reservation) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.gender = gender;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.state = state;
        this.country = country;
        this.zipCode = zipCode;
        this.reservation = reservation;
        this.email = email;
    }

    private Customer(){
        id = null;
        firstName = null;
        lastName = null;
        dob = null;
        gender = null;
        addressLine1 = null;
        state = null;
        addressLine2 = null;
        country = null;
        zipCode = null;
        reservation = null;
        email = null;
    }

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getDob() {
        return dob;
    }

    public String getGender() {
        return gender;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public String getState() {
        return state;
    }

    public String getCountry() {
        return country;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getEmail() {return email; }

    public Reservation getReservation() {
        return reservation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Customer that = (Customer) o;

        if (firstName != null ? !firstName.equals(that.getFirstName()) : that.getFirstName() != null) return false;
        if (lastName != null ? !lastName.equals(that.getLastName()) : that.getLastName() != null) return false;
        if (dob != null ? !dob.equals(that.getDob()) : that.getDob() != null) return false;
        if (gender != null ? !gender.equals(that.getGender()) : that.getGender() != null) return false;
        if (addressLine1 != null ? !addressLine1.equals(that.getAddressLine1()) : that.getAddressLine1() != null) return false;
        if (addressLine2 != null ? !addressLine2.equals(that.getAddressLine2()) : that.getAddressLine2() != null) return false;
        if (state != null ? !state.equals(that.getState()) : that.getState() != null) return false;
        if (country != null ? !country.equals(that.getCountry()) : that.getCountry() != null) return false;
        if (zipCode != null ? !zipCode.equals(that.getZipCode()) : that.getZipCode() != null) return false;
        if (email != null ? !email.equals(that.getEmail()) : that.getEmail() != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (dob != null ? dob.hashCode() : 0);
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        result = 31 * result + (addressLine1 != null ? addressLine1.hashCode() : 0);
        result = 31 * result + (addressLine2 != null ? addressLine2.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (zipCode != null ? zipCode.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dob=" + dob +
                ", gender='" + gender + '\'' +
                ", addressLine1='" + addressLine1 + '\'' +
                ", addressLine2='" + addressLine2 + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", zipCode='" + zipCode +
                '}';
    }

    @Override
    public int compareTo(Customer o) {
        return this.lastName.compareToIgnoreCase(o.lastName);
    }

    public static class Builder{
        private Long id;
        private String firstName;
        private String lastName;
        private LocalDate dob;
        private String gender;
        private String addressLine1;
        private String addressLine2;
        private String state;
        private String country;
        private String zipCode;
        private String email;

        private Reservation reservation;

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder setDob(LocalDate dob) {
            this.dob = dob;
            return this;
        }

        public Builder setGender(String gender) {
            this.gender = gender;
            return this;
        }

        public Builder setAddressLine1(String addressLine1) {
            this.addressLine1 = addressLine1;
            return this;
        }

        public Builder setAddressLine2(String addressLine2) {
            this.addressLine2 = addressLine2;
            return this;
        }

        public Builder setState(String state) {
            this.state = state;
            return this;
        }

        public Builder setCountry(String country) {
            this.country = country;
            return this;
        }

        public Builder setZipCode(String zipCode) {
            this.zipCode = zipCode;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setReservation(Reservation reservation) {
            this.reservation = reservation;
            return this;
        }

        public Customer build(){
            return new Customer(
                    id,
                    firstName,
                    lastName,
                    dob,
                    gender,
                    addressLine1,
                    addressLine2,
                    state,
                    country,
                    zipCode,
                    email,
                    reservation
            );
        }
    }


}
