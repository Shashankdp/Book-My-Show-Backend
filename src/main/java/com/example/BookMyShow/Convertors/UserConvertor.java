package com.example.BookMyShow.Convertors;

import com.example.BookMyShow.Dtos.UserDto;
import com.example.BookMyShow.Entities.User;

public class UserConvertor {

    //convertors can be written in static method
    //because static is kept to avoid calling it via objects/instances
    //(Static methos is used to avoid making abject of that class, we simply call fuction using classname.)
    public static User convertDtoToEntity(UserDto userDto){
        User user= User.builder().age(userDto.getAge()).name(userDto.getName())
                .email(userDto.getEmail()).address(userDto.getAddress()).mobileNo(userDto.getMobileNo()).build();

        return user;
    }
}
