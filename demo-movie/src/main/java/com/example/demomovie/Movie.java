package com.example.demomovie;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Movie {

    private static int MOVIE_CODE_SPECIAL = 1;
    private String title;
    private String description;
    private Duration runningTime;
    private double ticketPrice;
    private int specialCode;
    private Showing showing;

    public Movie(String title, Duration runningTime, double ticketPrice, int specialCode) {
        this.title = title;
        this.runningTime = runningTime;
        this.ticketPrice = ticketPrice;
        this.specialCode = specialCode;
    }

    public double calculateTicketPrice(Showing showing) {
        return ticketPrice - getDiscount(showing.getSequenceOfTheDay(),showing);
    }

    private double getDiscount(int showSequence,Showing showing) {
        double specialDiscount = 0;
        // Check for special movie code
        if (MOVIE_CODE_SPECIAL == specialCode) {
            specialDiscount = ticketPrice * 0.2;  // 20% discount for special movie
        }

        double discount = 0;
        // check for time (if time is between 11 am to 2 pm  will get discount )
        LocalDateTime localDateTime=showing.getShowStartTime();
        Date date2 = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        SimpleDateFormat formatTime = new SimpleDateFormat("HHmmss");
        String time = formatTime.format(date2);
        int a =Integer.parseInt(time);
        double timeDiscount=0;
        if(a>=110000 && a<=160000){
            timeDiscount=ticketPrice*0.25; // 25% discount if show time between 11 AM to 2 PM
        }

        if(specialDiscount>timeDiscount){discount=specialDiscount;}
        else{discount=timeDiscount;}

        //Check for showSequence
        double sequenceDiscount = 0;
        if (showSequence == 1) {sequenceDiscount = 3; // $3 discount for 1st show
        }
        else if (showSequence == 2) {
            sequenceDiscount = 2; // $2 discount for 2nd show
        }
        else if (showSequence == 7) {
            sequenceDiscount = 1; // $1 discount for 7th show
        }
        // biggest discount wins
        return discount > sequenceDiscount ? discount : sequenceDiscount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return Double.compare(movie.ticketPrice, ticketPrice) == 0
                && Objects.equals(title, movie.title)
                && Objects.equals(description, movie.description)
                && Objects.equals(runningTime, movie.runningTime)
                && Objects.equals(specialCode, movie.specialCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, runningTime, ticketPrice, specialCode);
    }
}