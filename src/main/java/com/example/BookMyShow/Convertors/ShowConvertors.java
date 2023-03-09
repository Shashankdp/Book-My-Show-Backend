package com.example.BookMyShow.Convertors;

import com.example.BookMyShow.Dtos.ShowDto;
import com.example.BookMyShow.Entities.Show;

public class ShowConvertors {

    public static Show convertDtoToEntity(ShowDto showDto){
        Show show=Show.builder().showDate(showDto.getShowDate())
                .showTime(showDto.getShowTime()).showType(showDto.getShowType())
                .build();

        return show;
    }
}
