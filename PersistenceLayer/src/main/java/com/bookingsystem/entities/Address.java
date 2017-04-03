package com.bookingsystem.entities;

import javax.persistence.*;

/**
 * Created by vijay on 4/1/17.
 */
@Entity
@Table(name = "ADDRESS")
public class Address extends BaseEntity implements Comparable<Address>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private final Long id;

    @Column(name = "ADDRESS_LINE_1")
    private final String addressLine1;

    @Column(name = "ADDRESS_LINE_2")
    private final String addressLine2;

    @Column(name = "CITY")
    private final String city;

    @Column(name = "COUNTRY")
    private final String country;

    @Column(name = "STATE")
    private final String state;

    @Column(name = "ZIP_CODE")
    private final String zipCode;

    public Address(Long id, String addressLine1, String addressLine2, String city, String country, String state, String zipCode, Location location, Customer customer) {
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.city = city;
        this.country = country;
        this.state = state;
        this.zipCode = zipCode;
        this.id = id;
    }

    private Address() {
        this.addressLine1 = null;
        this.addressLine2 = null;
        this.city = null;
        this.state = null;
        this.country = null;
        this.zipCode = null;
        this.id = null;

    }

    @Override
    public Long getId() {
        return id;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getState() {
        return state;
    }

    public String getZipCode() {
        return zipCode;
    }

    @Override
    public int compareTo(Address address) {
        return this.addressLine1.compareTo(address.addressLine1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Address address = (Address) o;

        if (getAddressLine1() != null ? !getAddressLine1().equals(address.getAddressLine1()) : address.getAddressLine1() != null)
            return false;
        if (getAddressLine2() != null ? !getAddressLine2().equals(address.getAddressLine2()) : address.getAddressLine2() != null)
            return false;
        if (getCity() != null ? !getCity().equals(address.getCity()) : address.getCity() != null) return false;
        if (getCountry() != null ? !getCountry().equals(address.getCountry()) : address.getCountry() != null)
            return false;
        if (getState() != null ? !getState().equals(address.getState()) : address.getState() != null) return false;
        return getZipCode() != null ? getZipCode().equals(address.getZipCode()) : address.getZipCode() == null;
    }

    @Override
    public int hashCode() {
        int result = getAddressLine1() != null ? getAddressLine1().hashCode() : 0;
        result = 31 * result + (getAddressLine2() != null ? getAddressLine2().hashCode() : 0);
        result = 31 * result + (getCity() != null ? getCity().hashCode() : 0);
        result = 31 * result + (getCountry() != null ? getCountry().hashCode() : 0);
        result = 31 * result + (getState() != null ? getState().hashCode() : 0);
        result = 31 * result + (getZipCode() != null ? getZipCode().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Address{" +
                "addressLine1='" + addressLine1 + '\'' +
                ", addressLine2='" + addressLine2 + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", state='" + state + '\'' +
                ", zipCode='" + zipCode + '\'' +
                '}';
    }

    public static class AddressBuilder {
        private Long id;
        private String addressLine1;
        private String addressLine2;
        private String city;
        private String state;
        private String country;
        private String zipCode;
        private Location location;
        private Customer customer;

        public AddressBuilder setId(Long id) {
            this.id = id;
            return this;
        }

        public AddressBuilder setAddressLine1(String addressLine1) {
            this.addressLine1 = addressLine1;
            return this;
        }

        public AddressBuilder setAddressLine2(String addressLine2) {
            this.addressLine2 = addressLine2;
            return this;
        }

        public AddressBuilder setCity(String city) {
            this.city = city;
            return this;
        }

        public AddressBuilder setState(String state) {
            this.state = state;
            return this;
        }

        public AddressBuilder setCountry(String country) {
            this.country = country;
            return this;
        }

        public AddressBuilder setZipCode(String zipCode) {
            this.zipCode = zipCode;
            return this;
        }

        public AddressBuilder setAddress(Address address) {
            this.id = address.getId();
            this.addressLine1 = address.getAddressLine1();
            this.addressLine2 = address.getAddressLine2();
            this.city = address.getCity();
            this.country = address.getCountry();
            this.zipCode = address.getZipCode();
            this.state = address.getState();
            return this;
        }

        public Address build() {
            return new Address(id, addressLine1, addressLine2, city, country, state, zipCode, location, customer);
        }
    }

}
