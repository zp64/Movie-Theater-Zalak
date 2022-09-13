package com.example.demomovie;

import lombok.*;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.persistence.Entity;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Showing  {
    private Movie movie;
    private int sequenceOfTheDay;
    private LocalDateTime showStartTime;

    public boolean isSequence(int sequence) {
        return this.sequenceOfTheDay == sequence;
    }
    public double getMovieFee() {
        return movie.calculateTicketPrice(this);
    }

    private double calculateFee(int audienceCount) {
        return movie.calculateTicketPrice(this) * audienceCount;
    }
}