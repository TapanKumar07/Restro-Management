package com.example.Hotel.Service.service;

import com.example.Hotel.Service.entitites.Hotel;

import java.util.List;

public interface HotelService {
    //create
    Hotel createHotel(Hotel hotel);
    //get all
    List<Hotel> getAllHotels();
    //get by id
    Hotel getHotelById(String hotelId);
}
