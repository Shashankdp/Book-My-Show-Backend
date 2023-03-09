package com.example.BookMyShow.Repositories;

import com.example.BookMyShow.Entities.TheaterSeats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TheaterSeatRepository extends JpaRepository<TheaterSeats,Integer> {
}
