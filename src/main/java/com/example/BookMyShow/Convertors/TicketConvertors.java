package com.example.BookMyShow.Convertors;

import com.example.BookMyShow.Dtos.TicketEntryDto;
import com.example.BookMyShow.Entities.Ticket;

public class TicketConvertors {

    public static Ticket convertDtoToEntity(TicketEntryDto ticketEntryDto){

       Ticket ticket=new Ticket();
        return ticket;
    }
}
