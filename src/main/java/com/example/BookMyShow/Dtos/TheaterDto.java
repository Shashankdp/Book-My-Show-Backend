package com.example.BookMyShow.Dtos;

import lombok.Data;

@Data
public class TheaterDto {

    private String name;
    private String location;

    private int classicSeatCount;
    private int premiumSeatCount;
}
