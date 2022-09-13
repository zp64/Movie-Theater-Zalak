package com.example.demomovie;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Assert;

import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Theater.class, loader = AnnotationConfigContextLoader.class)
public class TheaterTests {

    @Autowired
    Theater theater;

    @Test
    void totalFeeForCustomer() {
        Theater theater = new Theater(LocalDateProvider.singleton());
        Customer john = new Customer("John Doe", "id-12345");
        Reservation reservation = theater.reserve(john, 2, 4);
//        System.out.println("You have to pay " + reservation.getTotalFee());
        assertEquals(reservation.totalFee(), 50);
    }


    @Test // here we are getting discount price because show time in between 11 am to 2pm
    void totalFeeForNewCustomer() {
        Theater theater = new Theater(LocalDateProvider.singleton());
        theater.setSchedule((Arrays.asList(new Showing(new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 1), 1, LocalDateTime.of(LocalDate.now(), LocalTime.of(11, 0))))));
        Customer zalak = new Customer("Zalak Prajapati", "id-12567");
        Reservation reservation = theater.reserve(zalak, 1, 4);
       // System.out.println("You have to pay " + reservation.totalFee());
        assertEquals(reservation.totalFee(), 37.5);
    }

    @Test
    void printMovieSchedule() {

        Theater theater = new Theater(LocalDateProvider.singleton());
        theater.setSchedule((Arrays.asList(
                new Showing(new Movie("Turning Red", Duration.ofMinutes(85), 11, 0), 1, LocalDateTime.of(LocalDate.now(), LocalTime.of(9, 0))),
                new Showing(new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 1), 2, LocalDateTime.of(LocalDate.now(), LocalTime.of(11, 0))))));

        theater.printSchedule();


    }
}
