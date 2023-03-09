package com.example.BookMyShow.Entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="theaters")
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Theater {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String location;

    //This is the parent wrt to theaterSeats

    @OneToMany(mappedBy = "theater",cascade = CascadeType.ALL)
    private List<TheaterSeats> theaterSeatsList=new ArrayList<>();

    //this is parent wrt show entity.
    @OneToMany(mappedBy = "theater",cascade = CascadeType.ALL)
    private List<Show> showsList=new ArrayList<>();


}
