package com.example.BookMyShow.Entities;

import com.example.BookMyShow.Genres.Genre;
import com.example.BookMyShow.Genres.Language;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="Movies")
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true,nullable = false)
    private String movieName;
    private double rating;

    private int duration;
    @Enumerated(value = EnumType.STRING)
    private Genre genre;
    @Enumerated(value = EnumType.STRING)
    private Language language;


    //this is the parent wrt show Entity(one movie have multiple shows)
    @OneToMany(mappedBy = "movie",cascade = CascadeType.ALL)
    private List<Show> showList;



}
