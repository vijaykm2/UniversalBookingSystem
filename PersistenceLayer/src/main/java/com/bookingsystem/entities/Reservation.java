package com.bookingsystem.entities;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by vijay on 2/27/17.
 */
@Entity
@Table(name = "RESERVATION")
final public class Reservation extends BaseEntity implements Comparable<Reservation>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private final Long id;

    @Column(name = "RESERVATION_ID", unique = true, nullable = false)
    private final String reservationId;

    @Column(name = "CREATED_TIME")
    private final ZonedDateTime createdTime;

    @Column(name = "LAST_MODIFIED_TIME")
    private final ZonedDateTime lastModifiedTime;

    @Column(name = "ARRIVAL")
    private final String arrival;

    @Column(name = "DEPARTURE")
    private final String departure;

    @Column(name = "DEPARTURE_DATE_TIME")
    private final ZonedDateTime departureDateTime;

    @Column (name = "ARRIVAL_DATE_TIME")
    private final ZonedDateTime arrivalDateTime;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "reservation")
    private final Set<Customer> customers;

    private Reservation(Long id, String reservationId, ZonedDateTime createdTime, ZonedDateTime lastModifiedTime, String arrival, String departure, Set<Customer> customers, ZonedDateTime departureDateTime, ZonedDateTime arrivalDateTime) {
        this.id = id;
        this.reservationId = reservationId;
        this.createdTime = createdTime;
        this.lastModifiedTime = lastModifiedTime;
        this.arrival = arrival;
        this.departure = departure;
        this.customers = customers == null ? new HashSet<>() :  customers;
        this.arrivalDateTime = arrivalDateTime;
        this.departureDateTime = departureDateTime;
    }

    private Reservation() {

        reservationId = null;
        id=null;
        arrival = null;
        departure = null;
        createdTime = null;
        lastModifiedTime = null;
        customers = new HashSet<>();
        departureDateTime = null;
        arrivalDateTime = null;
    }

    public Long getId() {
        return id;
    }

    public String getReservationId() {
        return reservationId;
    }

    public ZonedDateTime getCreatedTime() {
        return createdTime;
    }

    public ZonedDateTime getLastModifiedTime() {
        return lastModifiedTime;
    }

    public String getArrival() {
        return arrival;
    }

    public String getDeparture() {
        return departure;
    }

    public Set<Customer> getCustomers() {
        return customers;
    }

    public ZonedDateTime getDepartureDateTime() {
        return departureDateTime;
    }

    public ZonedDateTime getArrivalDateTime() {
        return arrivalDateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Reservation that = (Reservation) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (reservationId != null ? !reservationId.equals(that.reservationId) : that.reservationId != null)
            return false;
        if (createdTime != null ? !createdTime.equals(that.createdTime) : that.createdTime != null) return false;
        if (lastModifiedTime != null ? !lastModifiedTime.equals(that.lastModifiedTime) : that.lastModifiedTime != null)
            return false;
        if (arrival != null ? !arrival.equals(that.arrival) : that.arrival != null) return false;
        if (departure != null ? !departure.equals(that.departure) : that.departure != null) return false;
        return customers != null && ((Reservation) o).customers != null && customers.size() ==((Reservation) o).customers.size() && customers.containsAll(((Reservation) o).customers) ;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (reservationId != null ? reservationId.hashCode() : 0);
        result = 31 * result + (createdTime != null ? createdTime.hashCode() : 0);
        result = 31 * result + (lastModifiedTime != null ? lastModifiedTime.hashCode() : 0);
        result = 31 * result + (arrival != null ? arrival.hashCode() : 0);
        result = 31 * result + (departure != null ? departure.hashCode() : 0);
        result = 31 * result + (customers != null || customers.size() > 0 ? customers.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", reservationId='" + reservationId + '\'' +
                ", createdTime=" + createdTime +
                ", lastModifiedTime=" + lastModifiedTime +
                ", arrival='" + arrival + '\'' +
                ", departure='" + departure + '\'' +
                ", customers=" + (customers != null && customers.size() > 0 ? customers : 0) +
                ", departure date tieme= " + departureDateTime.toString()+
                ", arrival date time = " + arrivalDateTime.toString() +
                '}';
    }

    @Override
    public int compareTo(Reservation o) {

        return this.reservationId.compareToIgnoreCase(o.reservationId);
    }
    
    public static class Builder {

        private Long id;
        private String reservationId;
        private ZonedDateTime createdTime;
        private ZonedDateTime lastModifiedTime;
        private String arrival;
        private String departure;
        private Set<Customer> customers;
        private ZonedDateTime arrivalDateTime;
        private ZonedDateTime departureDateTime;

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setReservationId(String reservationId) {
            this.reservationId = reservationId;
            return this;
        }

        public Builder setCreatedTime(ZonedDateTime createdTime) {
            this.createdTime = createdTime;
            return this;
        }

        public Builder setLastModifiedTime(ZonedDateTime lastModifiedTime) {
            this.lastModifiedTime = lastModifiedTime;
            return this;
        }

        public Builder setArrival(String arrival) {
            this.arrival = arrival;
            return this;
        }

        public Builder setDeparture(String departure) {
            this.departure = departure;
            return this;
        }

        public Builder setCustomers(Set<Customer> customers) {
            this.customers = customers;
            return this;
        }

        public Builder setArrivalDateTime(ZonedDateTime arrivalDateTime) {
            this.arrivalDateTime = arrivalDateTime ;
            return this;
        }

        public Builder setDepartureDateTime(ZonedDateTime departureDateTime) {
            this.departureDateTime = departureDateTime ;
            return this;
        }

        public Reservation build(){
            return new Reservation(id, reservationId, createdTime, lastModifiedTime, arrival, departure, customers, departureDateTime, arrivalDateTime);
        }
    }
}
