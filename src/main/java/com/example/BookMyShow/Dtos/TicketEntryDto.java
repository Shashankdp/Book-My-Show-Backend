package com.example.BookMyShow.Dtos;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class TicketEntryDto {

    private int userId;
    private int showId;
    private List<String> requestedSeats=new ArrayList<>();

}
