package com.bookingsystem.entities;

import javax.persistence.*;

/**
 * Created by vijay on 4/1/17.
 */
@Entity
@Table(name = "ADDRESS")
final public class Address extends BaseEntity implements Comparable<Address>{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    public final Long id;

    @Column(name = "ADDRESS_LINE_1")
    public final String addressLine1;

    @Column(name = "ADDRESS_LINE_2")
    public final String addressLine2;

    @Column(name = "CITY")
    public final String city;

    @Column(name = "COUNTRY")
    public final String country;

    @Column(name = "STATE")
    public final String state;

    @Column(name = "ZIP_CODE")
    public final String zipCode;

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

    @Override
    public int compareTo(Address address) {
        return this.addressLine1.compareTo(address.addressLine1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Address address = (Address) o;

        if (addressLine1 != null ? !addressLine1.equals(address.addressLine1) : address.addressLine1 != null)
            return false;
        if (addressLine2 != null ? !addressLine2.equals(address.addressLine2) : address.addressLine2 != null)
            return false;
        if (city != null ? !city.equals(address.city) : address.city != null) return false;
        if (country != null ? !country.equals(address.country) : address.country != null)
            return false;
        if (state != null ? !state.equals(address.state) : address.state != null) return false;
        return zipCode != null ? zipCode.equals(address.zipCode) : address.zipCode == null;
    }

    @Override
    public int hashCode() {
        int result = addressLine1 != null ? addressLine1.hashCode() : 0;
        result = 31 * result + (addressLine2 != null ? addressLine2.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (zipCode != null ? zipCode.hashCode() : 0);
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
            this.id = address.id;
            this.addressLine1 = address.addressLine1;
            this.addressLine2 = address.addressLine2;
            this.city = address.city;
            this.country = address.country;
            this.zipCode = address.zipCode;
            this.state = address.state;
            return this;
        }

        public Address build() {
            return new Address(id, addressLine1, addressLine2, city, country, state, zipCode, location, customer);
        }
    }

}
