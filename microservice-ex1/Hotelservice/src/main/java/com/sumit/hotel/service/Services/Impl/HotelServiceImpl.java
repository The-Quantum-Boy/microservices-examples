package com.sumit.hotel.service.Services.Impl;

import com.sumit.hotel.service.Services.HotelService;
import com.sumit.hotel.service.entities.Hotel;
import com.sumit.hotel.service.exceptions.ResourceNotFoundException;
import com.sumit.hotel.service.repositories.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HotelServiceImpl implements HotelService {


    @Autowired
    private HotelRepository hotelRepository;
    @Override
    public Hotel create(Hotel hotel) {
        String randomHotelId= UUID.randomUUID().toString();
        hotel.setId(randomHotelId);
        return hotelRepository.save(hotel);
    }

    @Override
    public List<Hotel> getAll() {
        return hotelRepository.findAll();
    }

    @Override
    public Hotel get(String id) {
        return hotelRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("hotel with given id not found "+id));
    }
}
