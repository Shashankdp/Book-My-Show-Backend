package com.example.BookMyShow.Convertors;

import com.example.BookMyShow.Dtos.MovieDto;
import com.example.BookMyShow.Entities.Movie;

public class MovieConvertors {

    public static Movie convertDtoToMovieEntity(MovieDto movieDto){
        Movie movie=Movie.builder().movieName(movieDto.getMovieName()).duration(movieDto.getDuration())
                .genre(movieDto.getGenre()).language(movieDto.getLanguage()).rating(movieDto.getRating()).build();

        return movie;
    }
}
