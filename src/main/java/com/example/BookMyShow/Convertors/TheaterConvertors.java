package com.example.BookMyShow.Convertors;

import com.example.BookMyShow.Dtos.TheaterDto;
import com.example.BookMyShow.Entities.Theater;

public class TheaterConvertors {

    public static Theater convertDtoToTheaterEntity(TheaterDto theaterDto){
        return Theater.builder().name(theaterDto.getName()).location(theaterDto.getLocation()).build();
    }
}
