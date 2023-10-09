package com.example.Hotel.Service.service.Impl;

import com.example.Hotel.Service.entitites.Hotel;
import com.example.Hotel.Service.excepions.ResourceNotFound.ResourceNotFoundException;
import com.example.Hotel.Service.respositry.HotelRepo;
import com.example.Hotel.Service.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HotelImpl implements HotelService {

    @Autowired
    private HotelRepo hotelDAO;

    @Override
    public Hotel createHotel(Hotel hotel) {
        String Id = UUID.randomUUID().toString();
        hotel.setHotelId(Id);
        return hotelDAO.save(hotel);
    }

    @Override
    public List<Hotel> getAllHotels() {
        return hotelDAO.findAll();
    }

    @Override
    public Hotel getHotelById(String hotelId) {
        return hotelDAO.findById(hotelId).orElseThrow(() -> new ResourceNotFoundException("Resource not found with id :" + hotelId));
    }
}
