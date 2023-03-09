package com.example.BookMyShow.Entities;

import com.example.BookMyShow.Genres.ShowType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="Shows")
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Show {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDate showDate;
    private LocalTime showTime;
    @Enumerated(value = EnumType.STRING)
    private ShowType showType;
    @CreationTimestamp
    private Date createdOn; //when was this show is actually created  // this attr will be automatically generated
    @UpdateTimestamp
    private Date updatedOn;          //this attr will be automatically generated

    //this is the child wrt to the movie Entity. (movie have a multiple shows)
    @ManyToOne
    @JoinColumn
    private Movie movie;

    //this is child wrt theater entity.
    @ManyToOne
    @JoinColumn
    private Theater theater;

    //this is parent wrt tickets entity. (show have list of tickets)
    @OneToMany(mappedBy = "show",cascade = CascadeType.ALL)
    private List<Ticket> listOfBookedTickets=new ArrayList<>();

    //this is parent wrt showseats entity.
    @OneToMany(mappedBy = "show",cascade = CascadeType.ALL)
    private List<ShowSeats> listOfShowSeats=new ArrayList<>();
}
