package com.example.BookMyShow.Entities;

import com.example.BookMyShow.Genres.SeatType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="theater_seats")
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class TheaterSeats {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Enumerated(value = EnumType.STRING)
    private SeatType seatType;
    private String seatNo;

    //this class is a child wrt theater class
    @ManyToOne
    @JoinColumn
    private Theater theater;  //inside a theater there is many theaterseats.


}
