package com.sumit.hotel.service.controller;

import com.sumit.hotel.service.Services.HotelService;
import com.sumit.hotel.service.entities.Hotel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @PreAuthorize("hasAuthority('Admin')")
    @PostMapping("/create")
    public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel){
        return ResponseEntity.status(HttpStatus.CREATED).body(hotelService.create(hotel));
    }


    @PreAuthorize("hasAuthority('SCOPE_internal')")
    @GetMapping("/{hotelId}")
    public ResponseEntity<Hotel> getById(@PathVariable String hotelId){
        return ResponseEntity.status(HttpStatus.CREATED).body(hotelService.get(hotelId));
    }


    @PreAuthorize("hasAuthority('SCOPE_internal') || hasAuthority('Admin')")
    @GetMapping("/getall")
    public ResponseEntity<List<Hotel>> getAll(){
        return ResponseEntity.status(HttpStatus.CREATED).body(hotelService.getAll());
    }



}
