package com.example.BookMyShow.Entities;

import com.example.BookMyShow.Genres.SeatType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="showseats")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShowSeats {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private boolean isBooked;

    private int price; //price of seat for that particular show
    private String seatNo;
    @Enumerated(value = EnumType.STRING)
    private SeatType seatType;

    private Date bookedAt;

    //this is child wrt show entity.
    @ManyToOne
    @JoinColumn
    private Show show;

}
