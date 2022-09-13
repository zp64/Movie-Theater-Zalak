package com.example.demomovie;

import lombok.var;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ReservationTests {

    @Test // we are getting discount on sequenceOfDay 1
    void totalFee() {
        var customer = new Customer("John Doe", "unused-id");
        var showing = new Showing(new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 0), 1, LocalDateTime.of(LocalDate.now(), LocalTime.of(10, 0)));
        assertTrue(new Reservation(customer, showing, 3).totalFee() == 28.5);
    }


    @Test //here we are getting discount price(Because movie time is between 11 am tto 2 pm) so totalFee would be 28.125 instead 37.5
    void specialMovieWithDiscount() {
        var customer = new Customer("Zalak Prajapati", "unused-id");
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),12.5, 1);
        Showing showing = new Showing(spiderMan, 1, LocalDateTime.of(LocalDate.now(), LocalTime.of(11, 0)));
        assertEquals(28.125, new Reservation(customer, showing, 3).totalFee());
    }
}
