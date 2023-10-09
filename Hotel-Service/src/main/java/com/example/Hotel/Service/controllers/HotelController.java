package com.example.Hotel.Service.controllers;

import com.example.Hotel.Service.entitites.Hotel;
import com.example.Hotel.Service.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @PostMapping
    private ResponseEntity<Hotel> createNewHotel(@RequestBody Hotel hotel) {
        Hotel new_hotel = hotelService.createHotel(hotel);
        return new ResponseEntity<Hotel>(new_hotel, HttpStatus.CREATED);
    }

    @GetMapping
    private ResponseEntity<List<Hotel>> getHotels() {
        List<Hotel> hotels = hotelService.getAllHotels();
        return new ResponseEntity<List<Hotel>>(hotels, HttpStatus.OK);
    }

    @GetMapping("/{hotelId}")
    private ResponseEntity<Hotel> getHotelById(@PathVariable String hotelId) {
        Hotel new_hotel = hotelService.getHotelById(hotelId);
        return new ResponseEntity<Hotel>(new_hotel, HttpStatus.OK);
    }

}
