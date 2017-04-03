package com.bookingsystem;

import com.bookingsystem.dao.CustomerDao;
import com.bookingsystem.dao.ReservationDao;
import com.bookingsystem.entities.Customer;
import com.bookingsystem.entities.Reservation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * Created by vijay on 2/27/17.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class})
public class ReservationDaoTest {

    @Autowired
    private ReservationDao reservationDao;

    @Autowired
    private CustomerDao customerDao;



    private String getReservationId(){
        return UUID.randomUUID().toString();
    }

    @Test
    @Transactional
    @Rollback(false)
    public void testInsertReservation(){



        Reservation reservation = new Reservation.Builder().setReservationId(getReservationId())
                .setArrival("DFW").setDeparture("EWR").setCreatedTime(ZonedDateTime.now())
                .setLastModifiedTime(ZonedDateTime.now())
                .setDepartureDateTime(ZonedDateTime.of(LocalDateTime.of(2017, 8, 8, 11, 30, 00), ZoneId.of(ZoneId.SHORT_IDS.get("CST"))))
                .setArrivalDateTime(ZonedDateTime.of(LocalDateTime.of(2017, 10, 10, 10, 10, 00), ZoneId.of(ZoneId.SHORT_IDS.get("EST"))))
                .build();
        reservationDao.saveOrUpdate(reservation);
        Customer pax1 = new Customer.Builder().setFirstName("Vijay")
                .setLastName("KM")
                .setDob(LocalDate.of(1984, 11, 30))
                .setAddressLine1("add line 1")
                .setAddressLine2("add line 2")
                .setGender("Male")
                .setCountry("India")
                .setState("KA")
                .setZipCode("192291")
                .setReservation(reservation).build();
        Customer pax2 = new Customer.Builder().setFirstName("Dhana")
                .setLastName("KM")
                .setDob(LocalDate.of(1992, 8, 9))
                .setAddressLine1("add line 1")
                .setAddressLine2("add line 2")
                .setGender("Female")
                .setCountry("India")
                .setState("KA")
                .setZipCode("192291")
                .setReservation(reservation).build();
        Customer pax3 = new Customer.Builder().setFirstName("sdsds")
                .setLastName("KM")
                .setDob(LocalDate.of(2017, 11, 30))
                .setAddressLine1("add line 1")
                .setAddressLine2("add line 2")
                .setGender("Male")
                .setCountry("India")
                .setState("KA")
                .setZipCode("192291")
                .setReservation(reservation).build();

        customerDao.saveOrUpdate(pax1,true);
        customerDao.saveOrUpdate(pax2, true);
        customerDao.saveOrUpdate(pax3, true);

        Set<Customer> passengers = reservation.getCustomers();
        passengers.add(pax1);
        passengers.add(pax2);
        passengers.add(pax3);
        reservationDao.saveOrUpdate(reservation);

        Reservation res = reservationDao.findReservationById(reservation.getReservationId());
        List<Customer> pax = customerDao.findPassengersByReservationId(reservation.getReservationId());
        pax.forEach(p -> System.out.println(p.toString()));

        Reservation updatedRes =  new Reservation.Builder().setId(res.getId()).setArrival(res.getArrival())
                .setArrivalDateTime(ZonedDateTime.ofInstant(res.getArrivalDateTime().toInstant(), res.getArrivalDateTime().getTimeZone().toZoneId()))
                .setDeparture("NYC")
                .setDepartureDateTime(ZonedDateTime.ofInstant(res.getDepartureDateTime().toInstant(), res.getDepartureDateTime().getTimeZone().toZoneId()))
                .setCreatedTime(res.getCreatedTime())
                .setCustomers(res.getCustomers())
                .setLastModifiedTime(ZonedDateTime.now())
                .setReservationId(res.getReservationId())
                .build();

        System.out.println();
        System.out.println(res.toString());

        reservationDao.saveOrUpdate(updatedRes, true);

        System.out.println(reservationDao.findReservationById(reservation.getReservationId()).toString());


    }
}
