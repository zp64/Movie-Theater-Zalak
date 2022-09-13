package com.example.demomovie;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {
    private Customer customer;
    private Showing showing;
    private int audienceCount;
    public double totalFee() {
        return showing.getMovieFee() * audienceCount;
    }
}