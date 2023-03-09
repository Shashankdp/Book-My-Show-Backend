package com.example.BookMyShow.Entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Entity
@Table(name="tickets")
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String movieName;
    private LocalDate showDate;
    private LocalTime showTime;

    private int totalAmount;
    private String ticketId= UUID.randomUUID().toString();
    private String theaterName;
    private String bookedSeats;

    //this is child wrt user Entity.
    @ManyToOne
    @JoinColumn
    private User user;


    //this is child wrt show entity.
    @ManyToOne
    @JoinColumn
    private Show show;


}
