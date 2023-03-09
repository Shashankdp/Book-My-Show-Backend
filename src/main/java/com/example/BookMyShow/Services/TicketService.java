package com.example.BookMyShow.Services;

import com.example.BookMyShow.Convertors.TicketConvertors;
import com.example.BookMyShow.Dtos.TicketEntryDto;
import com.example.BookMyShow.Entities.Show;
import com.example.BookMyShow.Entities.ShowSeats;
import com.example.BookMyShow.Entities.Ticket;
import com.example.BookMyShow.Entities.User;
import com.example.BookMyShow.Repositories.ShowRepository;
import com.example.BookMyShow.Repositories.TicketRepository;
import com.example.BookMyShow.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class TicketService {

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    ShowRepository showRepository;

    @Autowired
    UserRepository userRepository;

    public String addTicket(TicketEntryDto ticketEntryDto) throws Exception{

        //step1. create ticket entity from ticketDto ---> convert Dto to Entity

        Ticket ticket= TicketConvertors.convertDtoToEntity(ticketEntryDto);

        //validation: check all the requested seats are available or not.
        boolean isValidRequest=checkValidityOfRequestedSeats(ticketEntryDto);

        if(isValidRequest==false){
            throw new Exception("Requested seats are not available");
        }

        //we assume that requested seats are valid

        //lets calculate the total amount
        Show show=showRepository.findById(ticketEntryDto.getShowId()).get();
        List<ShowSeats> seatEntityList=show.getListOfShowSeats();

        List<String> requestedSeats=ticketEntryDto.getRequestedSeats();

        int totalAmount=0;
        for(ShowSeats showSeatEntity:seatEntityList){

            if(requestedSeats.contains(showSeatEntity.getSeatNo())){
               totalAmount=totalAmount+ showSeatEntity.getPrice();
               showSeatEntity.setBooked(true);
               showSeatEntity.setBookedAt(new Date());
            }
        }

//        ticket.setTicketId(UUID.randomUUID().toString()); //this line is not require, i already set in the Ticket entity
          ticket.setTotalAmount(totalAmount);

        //setting the foreign key attributes and other required attributes
        //setting other required attributes for ticket entity
        ticket.setMovieName(show.getMovie().getMovieName());
        ticket.setShowDate(show.getShowDate());
        ticket.setShowTime(show.getShowTime());
        ticket.setTheaterName(show.getTheater().getName());

        //we need to set that string that talked about requested seats
        String allotedSeats=getAllotedSeatsfromShowSeats(requestedSeats);
        ticket.setBookedSeats(allotedSeats);

        //setting the foriegn key attributes
        User user=userRepository.findById(ticketEntryDto.getUserId()).get();

        ticket.setUser((user));
        ticket.setShow(show);

        //save the parents ---> Show

        ticket=ticketRepository.save(ticket);


        List<Ticket> ticketList=show.getListOfBookedTickets();
        ticketList.add(ticket);
        show.setListOfBookedTickets(ticketList);

        showRepository.save(show);

        //save the parent ---> User
        List<Ticket> ticketList1=user.getBookedTickets();
        ticketList1.add(ticket);
        user.setBookedTickets(ticketList1);

        userRepository.save(user);

        return "Ticket has been successfully added";


    }
    private String getAllotedSeatsfromShowSeats(List<String> requestedSeats){

        String result="";

        for(String seat:requestedSeats){
            result=result + seat +", ";
        }
        return result;
    }

    private boolean checkValidityOfRequestedSeats(TicketEntryDto ticketEntryDto){

        int showId= ticketEntryDto.getShowId();

        List<String> requestedSeats=ticketEntryDto.getRequestedSeats();

        Show show=showRepository.findById(showId).get();

        List<ShowSeats> listOfSeats=show.getListOfShowSeats();

        //i am iterating over the list of seats for that particular show,
        for(ShowSeats showSeats:listOfSeats){

            String seatNo=showSeats.getSeatNo();

            if(requestedSeats.contains(seatNo)){

                if(showSeats.isBooked()==true){
                    return false;   //since this seat can't be occupied : returning false
                }
            }
        }
        //All the seats requested were available
        return true;
    }
}
