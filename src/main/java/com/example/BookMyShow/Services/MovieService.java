package com.example.BookMyShow.Services;

import com.example.BookMyShow.Convertors.MovieConvertors;
import com.example.BookMyShow.Dtos.MovieDto;
import com.example.BookMyShow.Entities.Movie;
import com.example.BookMyShow.Repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

    @Autowired
    MovieRepository movieRepository;

    public String addMovie(MovieDto movieDto) throws Exception{

        Movie movie= MovieConvertors.convertDtoToMovieEntity(movieDto);

        movieRepository.save(movie);

        return "Movie added successfully";
    }
}
