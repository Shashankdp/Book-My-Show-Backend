package com.example.BookMyShow.Dtos;

import com.example.BookMyShow.Genres.Genre;
import com.example.BookMyShow.Genres.Language;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;

@Data
public class MovieDto {
    private String movieName;
    private double rating;
    private int duration;
    private Genre genre;
    private Language language;

}
