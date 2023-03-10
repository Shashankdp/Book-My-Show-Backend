package com.example.BookMyShow.Controllers;

import com.example.BookMyShow.Dtos.MovieDto;
import com.example.BookMyShow.Services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    MovieService movieService;

    @PostMapping("/add_movie")
    public ResponseEntity<String> addMovie(@RequestBody MovieDto movieDto){

        try{
            String response=movieService.addMovie(movieDto);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }
        catch (Exception e){
             String result="Movie not added";
             return new ResponseEntity<>(result,HttpStatus.BAD_REQUEST);
        }
    }

}
