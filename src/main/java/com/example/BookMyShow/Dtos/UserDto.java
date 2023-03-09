package com.example.BookMyShow.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
@Data
//@NoArgsConstructor
//@AllArgsConstructor    //these two annotation will not required here.
public class UserDto {
    private String name;
    private int age;
    private String  email;
    private String mobileNo;
    private String address;
}
