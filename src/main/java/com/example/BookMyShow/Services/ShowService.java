package com.example.BookMyShow.Services;

import com.example.BookMyShow.Convertors.ShowConvertors;
import com.example.BookMyShow.Dtos.ShowDto;
import com.example.BookMyShow.Entities.*;
import com.example.BookMyShow.Genres.SeatType;
import com.example.BookMyShow.Repositories.MovieRepository;
import com.example.BookMyShow.Repositories.ShowRepository;
import com.example.BookMyShow.Repositories.TheaterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShowService {

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    TheaterRepository theaterRepository;

    @Autowired
    ShowRepository showRepository;

    public String addShow(ShowDto showDto){

        //step1. Create a show Entity
        Show show= ShowConvertors.convertDtoToEntity(showDto);

        int movieId=showDto.getMovieId();
        int theaterId= showDto.getTheaterId();

        Movie movie=movieRepository.findById(movieId).get();

        Theater theater=theaterRepository.findById(theaterId).get();

        //step2. setting the attributes of foriegn key
        show.setMovie(movie);
        show.setTheater(theater);

        //pending attributes are listOfShowSeats Entity

        List<ShowSeats> seatsList=createShowSeat(showDto,show);
        show.setListOfShowSeats(seatsList);

        //my goal is to create showSeatEntity

        //Now we also need to update the parent entities

                                                                  /*     List<Show> showList=movie.getShowList();
                                                                         showList.add(show);
                                                                         movie.setShowList(showList);
                                                                  */


        show=showRepository.save(show);

        movie.getShowList().add(show);
        theater.getShowsList().add(show);


        movieRepository.save(movie);

                                                                /*
                                                                    List<Show> showList1=theater.getShowsList();
                                                                    showList1.add(show);
                                                                    theater.setShowsList(showList1);
                                                                */

        theaterRepository.save(theater);

        return "The show has been added successfully";


    }
    private List<ShowSeats> createShowSeat(ShowDto showDto,Show show){


        //Now the goal is to create the ShowSeat Entity
        //we need to set it's attributes

        Theater theater=show.getTheater();


        List<TheaterSeats> theaterSeatsList=theater.getTheaterSeatsList();

        List<ShowSeats> seatsList=new ArrayList<>();

        for(TheaterSeats theaterSeats: theaterSeatsList){

            ShowSeats showSeats=new ShowSeats();

            showSeats.setSeatNo(theaterSeats.getSeatNo());
            showSeats.setSeatType((theaterSeats.getSeatType()));

            if(theaterSeats.getSeatType().equals(SeatType.CLASSIC)){
                showSeats.setPrice(showDto.getClassicSeatPrice());
            }
            else{
                showSeats.setPrice(showDto.getPremiumSeatPrice());
            }


            showSeats.setBooked(false);
            showSeats.setShow(show);  //parent: foreign key for the showSeat Entity

            seatsList.add(showSeats); //adding it to the list
        }

        return seatsList;
    }
}
