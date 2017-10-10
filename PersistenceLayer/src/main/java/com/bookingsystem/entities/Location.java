package com.bookingsystem.entities;

import javax.persistence.*;

/**
 * Created by vijay on 3/31/17.
 */

@Entity
@Table(name = "LOCATION")
final public class Location extends BaseEntity implements Comparable<Location> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    public final Long id;

    @Column(name = "LOCATION_NAME")
    public final String locationName;

    @Column(name = "LOCATION_CODE")
    public final String locationCode;

    @Column(name = "LOCATION_TYPE")
    @Enumerated(EnumType.STRING)
    public final LocationType locationType;

    @Column(name = "CITY")
    public final String city;

    @OneToOne( cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @MapsId
    @JoinColumn(name = "ADDRESS")
    public final Address address;

    private Location(Long id, String locationName, String locationCode, String city, Address address, LocationType locationType) {
        this.locationName = locationName;
        this.locationCode = locationCode;
        this.city = city;
        this.id = id;
        this.address = address;
        this.locationType = locationType;
    }

    private Location() {
        this.id = null;
        this.city = null;
        this.locationCode = null;
        this.locationName = null;
        this.address = null;
        this.locationType = null;
    }

    public Long getId() {
        return id;
    }
    
    @Override
    public String toString() {
        return "Location{" +
                "id=" + id +
                ", locationName='" + locationName + '\'' +
                ", locationCode='" + locationCode + '\'' +
                ", city='" + city + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Location location = (Location) o;

        if (locationName != null ? !locationName.equals(location.locationName) : location.locationName != null)
            return false;
        if (locationCode != null ? !locationCode.equals(location.locationCode) : location.locationCode != null)
            return false;
        return city != null ? city.equals(location.city) : location.city == null;
    }

    @Override
    public int hashCode() {
        int result = locationName != null ? locationName.hashCode() : 0;
        result = 31 * result + (locationCode != null ? locationCode.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        return result;
    }

    @Override
    public int compareTo(Location location) {
        return this.locationName.compareTo(location.locationName);
    }

    public LocationType getLocationType() {
        return locationType;
    }

    public static class LocationBuilder  {

        private Long id;

        private String locationName;
        private String locationCode;
        private String city;
        private Address address;
        private LocationType locationType;

        public LocationBuilder setId(Long id) {
            this.id = id;
            return this;
        }

        public LocationBuilder setLocationName(String locationName) {
            this.locationName = locationName;
            return this;
        }

        public LocationBuilder setLocationCode(String locationCode) {
            this.locationCode = locationCode;
            return this;
        }

        public LocationBuilder setCity(String city) {
            this.city = city;
            return this;
        }

        public LocationBuilder setAddress(Address address){
            this.address = address;
            return this;
        }

        public LocationBuilder setLocationType(LocationType locationType){
            this.locationType = locationType;
            return this;
        }

        public LocationBuilder setLocation(Location location) {
            this.id = location.id;
            this.locationCode = location.locationCode;
            this.locationName = location.locationName;
            this.city = location.city;
            this.locationType = location.getLocationType();
            return this;
        }

        public Location build() {
            return new Location(id, locationName, locationCode, city, address, locationType);
        }
    }

}
