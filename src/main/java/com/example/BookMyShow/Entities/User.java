package com.example.BookMyShow.Entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Users")
@Data    //---> @Getter,@Setter,@ToString,@RequiredArgsConstructor
@NoArgsConstructor
@Builder         // i want to build this entity.---->means set this entity
@AllArgsConstructor

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int age;
    @Column(unique = true,nullable = false)
    private String  email;
    @Column(unique = true)
    private String mobileNo;
    private String address;

    //this is parent wrt ticket entity.
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Ticket> bookedTickets=new ArrayList<>();
}
