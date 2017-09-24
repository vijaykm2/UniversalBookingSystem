package com.bookingsystem.entities;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Created by vijay on 2/27/17.
 */
@Entity
@Table(name = "CUSTOMER")
final public class Customer extends BaseEntity implements Comparable<Customer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private final Long id;

    @Column(name = "FIRST_NAME")
    private final String firstName;

    @Column(name = "MIDDLE_NAME")
    private final String middleName;

    @Column(name = "LAST_NAME")
    private final String lastName;

    @Column(name = "DATE_OF_BIRTH")
    private final LocalDate dob;

    @Column(name = "GENDER")
    private final String gender;

    @Column( name = "EMAIL_ID")
    private final String email;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (name = "RESERVATION")
    private final Reservation reservation;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "ADDRESS")
    private final Address address;


    private Customer(Long id, String firstName, String middleName, String lastName, LocalDate dob, String gender, String email, Reservation reservation, Address address) {
        this.id = id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.dob = dob;
        this.gender = gender;
        this.reservation = reservation;
        this.email = email;
        this.address = address;
    }

    private Customer(){
        id = null;
        firstName = null;
        middleName = null;
        lastName = null;
        dob = null;
        gender = null;
        reservation = null;
        email = null;
        address = null;
    }

    public Long getId() {
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
        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (dob != null ? dob.hashCode() : 0);
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
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
                '}';
    }

    @Override
    public int compareTo(Customer o) {
        return this.lastName.compareToIgnoreCase(o.lastName);
    }

    public Address getAddress() {
        return address;
    }

    public String getMiddleName() {
        return middleName;
    }

    public static class Builder{
        private Long id;
        private String firstName;
        private String middleName;
        private String lastName;
        private LocalDate dob;
        private String gender;
        private String addressLine1;
        private String addressLine2;
        private String state;
        private String country;
        private String zipCode;
        private String email;
        private String city;
        private Address address;

        private Reservation reservation;

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder setMiddleName(String middleName) {
            this.middleName = middleName;
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

        public Builder setAddress(Address address) {
            this.address = address;
            return this;
        }

        public Builder setCustomer(Customer customer) {
            address = customer.getAddress();
            id = customer.getId();
            lastName = customer.getLastName();
            middleName = customer.middleName;
            firstName = customer.getFirstName();
            gender = customer.getGender();
            return this;
        }

        public Customer build(){
            Address.AddressBuilder addressBuilder= new Address.AddressBuilder();
            if (address == null ){
                addressBuilder = new Address.AddressBuilder().setAddressLine1(addressLine1).setAddressLine2(addressLine2).setCity(city).setCountry(country).setZipCode(zipCode).setState(state);
                address = addressBuilder.build();
            }

            Customer cust = new Customer(
                    id,
                    firstName,
                    middleName,
                    lastName,
                    dob,
                    gender,
                    email,
                    reservation,
                    address
            );
            return cust;
        }
    }


}
