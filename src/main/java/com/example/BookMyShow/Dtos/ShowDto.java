package com.example.BookMyShow.Dtos;

import com.example.BookMyShow.Genres.ShowType;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class ShowDto {

   private LocalDate showDate;
   private LocalTime showTime;
    private ShowType showType;
    private int movieId;
    private int theaterId;
    private int classicSeatPrice;
    private int premiumSeatPrice;
}
