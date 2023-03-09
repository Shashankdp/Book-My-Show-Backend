package com.example.BookMyShow.Services;

import com.example.BookMyShow.Controllers.UserController;
import com.example.BookMyShow.Convertors.UserConvertor;
import com.example.BookMyShow.Dtos.UserDto;
import com.example.BookMyShow.Entities.User;
import com.example.BookMyShow.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public  String addUser(UserDto userDto) throws Exception{

//        //here i need to convert and save (converting userDto to user Entity)
//
//        /*
//         Old method : create an object and set attributes.
//
//         */
//
//        //using @Builder annotation you will to set all the attributes in one line itself.
//
//        User user=User.builder().age(userDto.getAge()).name(userDto.getName()).address(userDto.getAddress()).email(userDto.getEmail())
//                .mobileNo(userDto.getMobileNo()).build();
//
//        //this is to set all of the attributes in 1 go.
//
//        userRepository.save(user);



        User user= UserConvertor.convertDtoToEntity(userDto);
        //here convertDtoToEntity is a static function,so i will be calling it via class name.
        // (Static methos is used to avoid making abject of that class, we simply call fuction using classname.)

        userRepository.save(user);

        return "User added Successfully";

    }
}
