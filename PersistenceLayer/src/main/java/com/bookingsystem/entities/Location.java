package com.bookingsystem.entities;

import javax.persistence.*;

/**
 * Created by vijay on 3/31/17.
 */

@Entity
@Table(name = "LOCATION")
public class Location extends BaseEntity implements Comparable<Location> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private final Long id;

    @Column(name = "LOCATION_NAME")
    private final String locationName;

    @Column(name = "LOCATION_CODE")
    private final String locationCode;

    @Column(name = "LOCATION_TYPE")
    @Enumerated(EnumType.STRING)
    private final LocationType locationType;

    @Column(name = "CITY")
    private final String city;

    @OneToOne( cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @MapsId
    @JoinColumn(name = "ADDRESS")
    private final Address address;

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

    public String getLocationName() {
        return locationName;
    }

    public String getLocationCode() {
        return locationCode;
    }

    public String getCity() {
        return city;
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

        if (getLocationName() != null ? !getLocationName().equals(location.getLocationName()) : location.getLocationName() != null)
            return false;
        if (getLocationCode() != null ? !getLocationCode().equals(location.getLocationCode()) : location.getLocationCode() != null)
            return false;
        return getCity() != null ? getCity().equals(location.getCity()) : location.getCity() == null;
    }

    @Override
    public int hashCode() {
        int result = getLocationName() != null ? getLocationName().hashCode() : 0;
        result = 31 * result + (getLocationCode() != null ? getLocationCode().hashCode() : 0);
        result = 31 * result + (getCity() != null ? getCity().hashCode() : 0);
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
            this.id = location.getId();
            this.locationCode = location.getLocationCode();
            this.locationName = location.getLocationName();
            this.city = location.getCity();
            this.locationType = location.getLocationType();
            return this;
        }

        public Location build() {
            return new Location(id, locationName, locationCode, city, address, locationType);
        }
    }

}
