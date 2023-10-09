package com.example.Hotel.Service.respositry;

import com.example.Hotel.Service.entitites.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepo extends JpaRepository<Hotel, String> {
}
