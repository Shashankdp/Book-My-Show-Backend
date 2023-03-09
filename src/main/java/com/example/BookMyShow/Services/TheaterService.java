package com.example.BookMyShow.Services;

import com.example.BookMyShow.Convertors.TheaterConvertors;
import com.example.BookMyShow.Dtos.TheaterDto;
import com.example.BookMyShow.Entities.Theater;
import com.example.BookMyShow.Entities.TheaterSeats;
import com.example.BookMyShow.Genres.SeatType;
import com.example.BookMyShow.Repositories.TheaterRepository;
import com.example.BookMyShow.Repositories.TheaterSeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TheaterService {

//    @Autowired
//    TheaterSeatRepository theaterSeatRepository;

    @Autowired
    TheaterRepository theaterRepository;

    public String addTheater(TheaterDto theaterDto) throws Exception{

//        //do some validations :
//        if(theaterDto.getName()==null || theaterDto.getLocation()==null){
//            throw new Exception("Name and Location Shouldn't Valid");
//        }


        Theater theater= TheaterConvertors.convertDtoToTheaterEntity(theaterDto);

        //some of the attributes are set and some attributes are not set,
        List<TheaterSeats> theaterSeatsList=createTheaterSeats(theaterDto,theater);

        theater.setTheaterSeatsList(theaterSeatsList);

        theaterRepository.save(theater);

        return "Theater added successfully";
    }

    private List<TheaterSeats> createTheaterSeats(TheaterDto theaterDto,Theater theater){
        int noClassicSeats= theaterDto.getClassicSeatCount();
        int noPremiumSeats= theaterDto.getPremiumSeatCount();

        List<TheaterSeats> theaterSeatsList=new ArrayList<>();

        //creation of classic seats
        for(int count=1;count<=noClassicSeats;count++){

            //we need to make a new theaterSeat Entity

            TheaterSeats theaterSeats=TheaterSeats.builder().seatType(SeatType.CLASSIC)
                    .seatNo(count+"C").theater(theater).build();

            theaterSeatsList.add(theaterSeats);
        }

        //create premium seats
        for(int count=1;count<=noPremiumSeats;count++){
            TheaterSeats theaterSeats=TheaterSeats.builder().seatType(SeatType.PREMIUM)
                    .seatNo(count+"P").theater(theater).build();

            theaterSeatsList.add(theaterSeats);
        }

//        theaterSeatRepository.saveAll(theaterSeatsList);
        //i am not saving the child here.
        //there is no need to save this, when parent saved child will automatically save.

        return theaterSeatsList;
    }
}
